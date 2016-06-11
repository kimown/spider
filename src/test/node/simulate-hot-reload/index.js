/**
 * 模拟热更新
 * ref: http://fex.baidu.com/blog/2015/05/nodejs-hot-swapping/
 * Created by google on 16-6-11.
 */


'use strict';
function cleanCache (module) {
    var path = require.resolve(module);
    require.cache[path] = null;
}

setInterval(function () {
    cleanCache('./code.js');
    var code = require('./code.js');
    console.log(code);
}, 1000);