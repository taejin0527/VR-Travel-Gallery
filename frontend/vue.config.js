const webpack = require('webpack');

module.exports = {
  transpileDependencies: ["vuetify"],
  configureWebpack: {
    plugins: [
      new webpack.ProvidePlugin({
        'introJs': ['intro.js']
      })
    ]
  },
  devServer: {
    host: "0.0.0.0",
    hot: true,
    disableHostCheck: true
  },
};