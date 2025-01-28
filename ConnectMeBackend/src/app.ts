import { Server } from "./server";
import type { Router } from "express";
import { userRoutes } from "./user/infraestructure/driving-adapters/api-rest/user.routes";

(() => {
  main();
})();

function main(): void {
  const server = new Server();
  const routes: Router[] = [userRoutes];
  server.addRoutes(routes);
  server.start();
}
