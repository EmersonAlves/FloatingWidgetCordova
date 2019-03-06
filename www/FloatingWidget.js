module.exports.open = function (successCallback, errorCallback) {
    cordova.exec(()=>{successCallback()},()=>{errorCallback()}, "FloatingWidget", "open", []);
};

module.exports.getPermission = function (successCallback, errorCallback) {
    cordova.exec(()=>{successCallback()},()=>{errorCallback()}, "FloatingWidget", "getPermission", []);
}