import type { NextFunction, Request, Response } from "express";
import { UserPrismaRepository } from "@src/user/infraestructure/driven-adapters/UserPrismaRepository";
import { CreateUser } from "@src/user/application/usecases/CreateUser";

export const createUser = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
  const { email, firstName, lastName, password } = req.body;
  const prismaRepository = new UserPrismaRepository();
  const createUserUseCase = new CreateUser(prismaRepository);
  
  try {
    const user = await createUserUseCase.run({ email, firstName, lastName, password });
    res.json(user);
  } catch (e) {
    next(e);
  }
};