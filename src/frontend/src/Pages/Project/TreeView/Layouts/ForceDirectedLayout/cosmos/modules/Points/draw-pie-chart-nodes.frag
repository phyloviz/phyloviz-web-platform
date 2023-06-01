#ifdef GL_ES
precision highp float;
#endif

// number of parts
#define MAX_NUM_SLICES 20
// automatically close the chart to full circle
#define CLOSED 1

// constants
#define PI 3.1415926535897932384626433832795

const vec4 Gray = vec4(0.5, 0.5, 0.5, 1);
uniform float u_time;

float intersect(float d1, float d2)
{
    return max(d1, d2);
}

float merge(float d1, float d2)
{
    return min(d1, d2);
}

float pie(vec2 p, float angle)
{
    vec2 n = vec2(-cos(angle), sin(angle));
    return p.x * n.x + p.y*n.y;
}

float sceneDist(vec2 p, vec2 c, inout vec4 color, int numSlices, float angles[MAX_NUM_SLICES], vec3 colors[MAX_NUM_SLICES])
{
    float time = u_time;
    vec2 center = c;
    p = p - center;

    float a[MAX_NUM_SLICES];

    for (int i = 0; i < MAX_NUM_SLICES; i++)
    {
        if (i >= numSlices) break;
        #ifdef ANIMATE
        a[i] = 2.0*angles[i] + angles[i] * cos(time * float(i+1) + float(i));
        #else
        a[i] = angles[i];
        #endif
    }

    float cc = 0.0;
    float start = 0.0;
    float circle = length(p) - center.y;

    for (int i = 0; i < MAX_NUM_SLICES + CLOSED; i++)
    {
        if (i >= numSlices + CLOSED) break;
        float end;
        vec4 pieColor;
        if (i == numSlices)
        {
            end = 2.0 * PI;
            pieColor = Gray;
        }
        else
        {
            end = start + a[i];
            pieColor = vec4(colors[i], 1.0);
        }

        float c = pie(p, start);
        float c2 = pie(p, end);
        float delta = end - start;
        if (delta < PI)
        cc = intersect(c, 1.0 - c2);
        else
        cc = merge(c, 1.0 - c2);
        cc = intersect(circle, cc);
        color = mix(color, pieColor, clamp(1.0-cc, 0.0, 1.0));
        start = end;
    }

    return cc;
}

const float smoothing = 0.99;

uniform vec2 u_resolution;
uniform sampler2D anglesAndColors;
uniform sampler2D slicesPerNodeTexture;
uniform vec2 anglesAndColorsTextureSize;
uniform vec2 slicesPerNodeTextureSize;
varying float _nodeIndex;
varying float alpha;
varying vec3 rgbColor;
void main() {
    if (alpha == 0.0) {
        discard;
    }

    float r = 0.0;
    float delta = 0.0;
    vec2 cxy = 2.0 * gl_PointCoord - 1.0;
    r = dot(cxy, cxy);
    float opacity = alpha* (1.0 - smoothstep(smoothing, 1.0, r));

    vec2 ref = vec2(100000.0);
    vec2 p = gl_PointCoord.xy * ref;
    vec2 c = ref / 2.0;

    vec2 slicesUv = (vec2(0, _nodeIndex) + 0.5) / slicesPerNodeTextureSize;
    int numSlices = int(floor(texture2D(slicesPerNodeTexture, slicesUv).x));

    float angles[MAX_NUM_SLICES];
    vec3 colors[MAX_NUM_SLICES];

    for (int i = 0; i < MAX_NUM_SLICES; i++)
    {
        if (i >= numSlices) break;
        vec2 uv = (vec2(0, _nodeIndex) + 0.5) / anglesAndColorsTextureSize;
        if (i == 0){
            vec4 colorAndAngle = texture2D(anglesAndColors, uv);
            angles[i] = colorAndAngle.r;
            colors[i] = colorAndAngle.gba;
        } else {
            vec4 colorAndAngle = texture2D(anglesAndColors, uv +  vec2(float(i) / anglesAndColorsTextureSize.x, 0));
            angles[i] = colorAndAngle.r;
            colors[i] = colorAndAngle.gba;
        }
    }

    if (numSlices == 0) {
        gl_FragColor = vec4(Gray.xyz, opacity);
        return;
    }

    // background
    vec4 col = vec4(0.0);
    float dist = sceneDist(p, c, col, numSlices, angles, colors);
    if (col.a == 0.0)
    discard;


    gl_FragColor = vec4(col.xyz, opacity);
}
