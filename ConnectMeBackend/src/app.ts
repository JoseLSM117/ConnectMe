import { Server } from "./server";
import { userRoutes } from "./user/infraestructure/driving-adapters/api-rest/user.routes";

(() => {
  main();
})();

function main(): void {
  const server = new Server();
  server.addRoutes(userRoutes, "/user");
  server.start();
}
