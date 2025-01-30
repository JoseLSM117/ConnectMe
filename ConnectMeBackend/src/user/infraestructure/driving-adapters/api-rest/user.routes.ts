import { type Request, type Response, Router } from "express";
import { createUser } from "./controllers/CreateUserController";
import { envs } from "@src/shared/infaestructure/env/envs";

export const userRoutes: Router = Router();
userRoutes.get("/", createUser);
userRoutes.get("/cambio", (req:Request, res:Response)=>{
  console.log(envs.API_URL);
  
  res.json({nose:"Test 2"});
});