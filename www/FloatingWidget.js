module.exports.open = function (successCallback, errorCallback) {
    cordova.exec(()=>{successCallback()},()=>{errorCallback()}, "FloatingWidget", "open", []);
};

module.exports.close = function (successCallback, errorCallback) {
    cordova.exec(()=>{successCallback()},()=>{errorCallback()}, "FloatingWidget", "close", []);
}

module.exports.getPermission = function (successCallback, errorCallback) {
    cordova.exec(()=>{successCallback()},()=>{errorCallback()}, "FloatingWidget", "getPermission", []);
}