"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Server = void 0;
require("module-alias/register");
const express_1 = __importDefault(require("express"));
class Server {
    constructor() {
        this._port = 3000;
        this._app = (0, express_1.default)();
        this._app.use(express_1.default.json());
        this._app.use(express_1.default.urlencoded({ extended: false }));
    }
    addRoutes(routes) {
        routes.forEach(route => {
            this._app.use(route);
        });
    }
    start() {
        this._app.listen(this._port, () => {
            console.log('App listen in port 3000');
        });
    }
}
exports.Server = Server;
