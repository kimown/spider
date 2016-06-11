/**
 *
 * 用nodejs代替java实现爬虫功能.
 * java主要负责数据的录入和输入,包括以后的计算分析功能全部放在java层次处理
 *
 * Created by google on 16-6-11.
 */

'use strict';

const http = require('http');


//http://stackoverflow.com/questions/4505809/how-to-post-to-a-request-using-node-js

var body = JSON.stringify({
    foo: "bar"
})

var request = new http.ClientRequest({
    hostname: "127.0.0.1",
    port: 9001,
    path: "/setjson",
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        "Content-Length": Buffer.byteLength(body)
    }
})



request.end(body);




/**

 浏览器里面模拟ajax请求.

 fetch("http://localhost:9001/setjson", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: '{"a":1}'
}).then(function(res) {

}, function(e) {

});




 **/