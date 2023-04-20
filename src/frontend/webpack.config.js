
module.exports = {
    mode: "development",
    resolve: {
        extensions: [".js", ".ts", ".tsx", ".css", ".ico"]
    },
    devServer: {
        historyApiFallback: true,
        port: 4200
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif|ico)$/,
                exclude: /node_modules/,
                use: ['file-loader?name=[name].[ext]']
            }
        ]
    }
}
