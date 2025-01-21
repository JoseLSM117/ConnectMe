"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const user_routes_1 = require("@/user/infraestructure/driving-adapters/routes/user.routes");
const server_1 = require("./server");
(() => {
    main();
})();
function main() {
    const server = new server_1.Server();
    const routes = [user_routes_1.userRoutes];
    server.addRoutes(routes);
    server.start();
}
