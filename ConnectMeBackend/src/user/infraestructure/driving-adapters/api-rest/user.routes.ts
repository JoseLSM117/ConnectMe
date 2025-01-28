import { type Request, type Response, Router } from "express";
import { createUser } from "./controllers/CreateUserController";

export const userRoutes: Router = Router();
userRoutes.get("/", createUser);
userRoutes.get("/cambio", (req:Request, res:Response)=>{
  res.json({nose:"Test 2"});
});