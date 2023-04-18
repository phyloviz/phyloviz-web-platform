package org.phyloviz.pwp.compute.service.flowviz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import okhttp3.*;
import org.phyloviz.pwp.compute.service.flowviz.adapters.AccessDeserializer;
import org.phyloviz.pwp.compute.service.flowviz.adapters.AccessSerializer;
import org.phyloviz.pwp.compute.service.flowviz.adapters.LocalDateTimeDeserializer;
import org.phyloviz.pwp.compute.service.flowviz.adapters.LocalDateTimeSerializer;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.ConnectionRefusedException;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.identity.Token;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;

import java.io.IOException;
import java.time.LocalDateTime;

// TODO: Comment this class

/**
 * Service for communicating with FLOWViZ API.
 */
public class FLOWViZHttpService {

    private final String baseUrl;
    private final OkHttpClient client;
    private final Gson gson;
    protected Token token;

    public FLOWViZHttpService(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = new OkHttpClient();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Access.class, new AccessSerializer())
                .registerTypeAdapter(Access.class, new AccessDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
    }

    public FLOWViZHttpService(FLOWViZHttpService httpService) {
        this.baseUrl = httpService.baseUrl;
        this.client = httpService.client;
        this.gson = httpService.gson;
        this.token = httpService.token;
    }

    private <T> T execute(
            Request request,
            Class<T> resClazz
    ) throws ConnectionRefusedException, UnexpectedResponseException {
        try (Response response = this.client.newCall(request).execute()) {

            if (!response.isSuccessful())
                throw new UnexpectedResponseException(response);

            ResponseBody responseBody = response.body();

            if (responseBody == null)
                throw new IllegalStateException("Response body is null");

            try {
                JsonReader reader = new JsonReader(responseBody.charStream());
                if (resClazz == null || resClazz == Void.class || resClazz == Void.TYPE)
                    return null;
                return gson.fromJson(reader, resClazz);
            } catch (JsonSyntaxException e) {
                throw new UnexpectedResponseException(response);
            }
        } catch (IOException e) {
            throw new ConnectionRefusedException(e);
        }
    }

    private Request addTokenToRequest(Request.Builder builder) {
        if (this.token != null)
            return builder.header("Authorization", "Bearer " + this.token.getToken()).build();
        else
            return builder.build();
    }

    private RequestBody generateRequestBody(Object body) {
        return RequestBody.create(
                this.gson.toJson(body),
                MediaType.parse("application/json")
        );
    }

    private <T> T execute(Request.Builder reqBuilder, String path, Class<T> resClazz) throws ConnectionRefusedException, UnexpectedResponseException {
        reqBuilder = reqBuilder
                .url(this.baseUrl + path);

        Request request = addTokenToRequest(reqBuilder);

        return execute(request, resClazz);
    }

    public <T> T post(String path, Object body, Class<T> resClazz) throws ConnectionRefusedException, UnexpectedResponseException {
        RequestBody requestBody = generateRequestBody(body);

        return execute(
                new Request.Builder()
                        .post(requestBody),
                path,
                resClazz
        );
    }

    public <T> T put(String path, Object body, Class<T> resClazz) throws ConnectionRefusedException, UnexpectedResponseException {
        RequestBody requestBody = generateRequestBody(body);

        return execute(
                new Request.Builder()
                        .put(requestBody),
                path,
                resClazz
        );
    }

    public <T> T get(String path, Class<T> resClazz) throws ConnectionRefusedException, UnexpectedResponseException {
        return execute(
                new Request.Builder()
                        .get(),
                path,
                resClazz
        );
    }

    public <T> T delete(String path, Class<T> resClazz) throws ConnectionRefusedException, UnexpectedResponseException {
        return execute(
                new Request.Builder()
                        .delete(),
                path,
                resClazz
        );
    }
}
