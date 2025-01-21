import { type NextFunction, type Request, type Response } from "express"

export class UserControllers {
  initRoutes = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    res.send("Tumama 2")
  }
}
