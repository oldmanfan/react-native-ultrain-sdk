"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
Object.defineProperty(exports, "__esModule", { value: true });
var react_native_1 = require("react-native");
var UltrainApiNative = react_native_1.NativeModules.UltrainSdkModule;
var UChainEndpoints;
(function (UChainEndpoints) {
    UChainEndpoints["MainNet"] = "https://ultrainio.ultrain.info";
    UChainEndpoints["PioneerNet"] = "https://pioneer.ultrain.info";
    UChainEndpoints["UnitopiaNet"] = "https://unitopia.ultrain.info";
    UChainEndpoints["NewRetailNet"] = "https://newretail.ultrain.info";
    UChainEndpoints["AustraliaNet"] = "https://australia.ultrain.info";
    UChainEndpoints["SafariNet"] = "https://safari.ultrain.info";
    UChainEndpoints["TestNet"] = "https://test-main.ultrain.info";
    UChainEndpoints["TestPioneerNet"] = "https://test-pioneer.ultrain.info";
    UChainEndpoints["TestPowerNet"] = "https://test-power.ultrain.info";
})(UChainEndpoints = exports.UChainEndpoints || (exports.UChainEndpoints = {}));
// type RsaKeyPair = any;
var Crypto = /** @class */ (function () {
    function Crypto() {
    }
    Crypto.sha256 = function (message) { return __awaiter(void 0, void 0, void 0, function () {
        var digest;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.sha256(message)];
                case 1:
                    digest = _a.sent();
                    return [2 /*return*/, digest];
            }
        });
    }); };
    return Crypto;
}());
exports.Crypto = Crypto;
var ECC = /** @class */ (function () {
    function ECC() {
    }
    /**
     * 生成一对公私钥及助记词
     */
    ECC.generatePrivateKeyWithMnemonic = function () { return __awaiter(void 0, void 0, void 0, function () {
        var rsa;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.generatePrivate()];
                case 1:
                    rsa = _a.sent();
                    return [2 /*return*/, rsa];
            }
        });
    }); };
    /**
     * 通过助记词生成公私钥
     * @param mnemonics 助记词
     */
    ECC.generatePrivateKeyByMnemonic = function (mnemonics) { return __awaiter(void 0, void 0, void 0, function () {
        var rsa;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.seedPrivate(mnemonics)];
                case 1:
                    rsa = _a.sent();
                    return [2 /*return*/, rsa];
            }
        });
    }); };
    /**
     * 通过私钥计算公钥
     * @param privateKey 私钥
     */
    ECC.getPublicKey = function (privateKey) { return __awaiter(void 0, void 0, void 0, function () {
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.getPublicKey(privateKey)];
                case 1: return [2 /*return*/, _a.sent()];
            }
        });
    }); };
    /**
     * 对交易进行签名, 但是不发送交易
     * @param url 链地址
     * @param txInfo 交易信息
     */
    ECC.signTx = function (url, txInfo) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.signTx(url, txInfo)];
                case 1:
                    rsp = _a.sent();
                    if (rsp.startsWith('SIG_K1_')) {
                        return [2 /*return*/, rsp];
                    }
                    throw new Error('Sign Tx error: ' + rsp);
            }
        });
    }); };
    return ECC;
}());
exports.ECC = ECC;
function parseRpcResult(rsp) {
    try {
        var obj = JSON.parse(rsp);
        if (obj.isSuccess === true) {
            return obj.data;
        }
        else {
            throw new Error(obj.message);
        }
    }
    catch (e) {
        throw e;
    }
}
var UChain = /** @class */ (function () {
    function UChain() {
    }
    /**
     * 获取账号的信息
     * @param url 链地址
     * @param account 账号
     */
    UChain.getAccount = function (url, account) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.getAccount(url, account)];
                case 1:
                    rsp = _a.sent();
                    data = parseRpcResult(rsp);
                    return [2 /*return*/, JSON.parse(data)];
            }
        });
    }); };
    /**
     * 获取账号的公钥信息
     * @param url 链地址
     * @param account 账号
     */
    UChain.getPublicKeysOfAccount = function (url, account) { return __awaiter(void 0, void 0, void 0, function () {
        var accountInfo, permInfo;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UChain.getAccount(url, account)];
                case 1:
                    accountInfo = _a.sent();
                    permInfo = { active: [], owner: [] };
                    accountInfo.permissions.forEach(function (p) {
                        permInfo[p.perm_name] = p.required_auth.keys;
                    });
                    return [2 /*return*/, permInfo];
            }
        });
    }); };
    /**
     * 获取链信息
     * @param url 链地址
     */
    UChain.getChainInfo = function (url) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.getChainInfo(url)];
                case 1:
                    rsp = _a.sent();
                    data = parseRpcResult(rsp);
                    return [2 /*return*/, JSON.parse(data)];
            }
        });
    }); };
    /**
     * 查询账号的余额
     * @param url 链地址
     * @param params 合约和账号信息
     */
    UChain.getCurrencyBalance = function (url, params) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.getCurrencyBalance(url, params)];
                case 1:
                    rsp = _a.sent();
                    data = parseRpcResult(rsp);
                    return [2 /*return*/, JSON.parse(data)];
            }
        });
    }); };
    /**
     * 查询交易手续费
     * @param url 链地址
     * @param blockHeight 链上某个块的块高
     */
    UChain.getTransferFee = function (url, blockHeight) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.getTransferFee(url, blockHeight)];
                case 1:
                    rsp = _a.sent();
                    data = parseRpcResult(rsp);
                    return [2 /*return*/, JSON.parse(data)];
            }
        });
    }); };
    /**
     * 发送交易
     * @param url 链地址
     * @param txInfo 交易信息
     */
    UChain.pushTransaction = function (url, txInfo) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.pushTx(url, txInfo)];
                case 1:
                    rsp = _a.sent();
                    data = parseRpcResult(rsp);
                    return [2 /*return*/, JSON.parse(data)];
            }
        });
    }); };
    /**
     * 转账操作
     * @param url 链地址
     * @param transferInfo 转账的信息
     */
    UChain.transfer = function (url, transferInfo) { return __awaiter(void 0, void 0, void 0, function () {
        var rsp, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, UltrainApiNative.transfer(url, transferInfo)];
                case 1:
                    rsp = _a.sent();
                    data = parseRpcResult(rsp);
                    return [2 /*return*/, JSON.parse(data)];
            }
        });
    }); };
    return UChain;
}());
exports.UChain = UChain;
