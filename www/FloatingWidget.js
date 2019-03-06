module.exports.open = function (successCallback, errorCallback) {
    console.log(1);
    cordova.exec(()=>{successCallback(1)},()=>{errorCallback(2)}, "FloatingWidget", "open", []);
};

module.exports.getPermission = function (successCallback, errorCallback) {
    console.log(2);
    cordova.exec(()=>{successCallback(1)},()=>{errorCallback(2)}, "FloatingWidget", "getPermission", []);

}