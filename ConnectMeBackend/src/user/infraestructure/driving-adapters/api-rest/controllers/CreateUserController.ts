import type { NextFunction, Request, Response } from "express";
import { UserPrismaRepository } from "@src/user/infraestructure/driven-adapters/UserPrismaRepository";
import { CreateUser } from "@src/user/application/usecases/CreateUser";
import { EmailTemplateService } from "@src/shared/application/services/emailService/EmailTemplateService";
import { envs } from "@src/shared/infaestructure/env/envs";
import { EmailServiceImpl } from "@src/shared/infaestructure/driven-adapters/emailService/EmailService";
import { JwtServiceImpl } from "@src/shared/infaestructure/driven-adapters/jwtService/JwtService";
import { hash } from "bcrypt";
import { FindUser } from "@src/user/application/usecases/FindUser";

export const createUser = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
  const { email, firstName, lastName, password } = req.body;
  const prismaRepository = new UserPrismaRepository();
  const createUserUseCase = new CreateUser(prismaRepository);
  const findUserUseCase = new FindUser(prismaRepository);
  const templateService = new EmailTemplateService();
  const emailService = new EmailServiceImpl({
    host: envs.MAILER_HOST,
    pass: envs.MAILER_PASSWORD,
    port: Number(envs.MAILER_PORT),
    user: envs.MAILER_EMAIL,
  });
  const jwtService = new JwtServiceImpl();
  try {
    const userFinded = await findUserUseCase.run({ email });
    if (userFinded?.userIsVerify.value) {
      res.json({ message: "User already registered and verified, please log in", status: 200 });
      return;
    }
    if (userFinded?.userIsVerify.value === false) {
      const token = jwtService.createAccessToken({ body: { user: userFinded.userId?.value } });
      const emailTemplate = templateService.compile("confirmationEmail", {
        name: `${userFinded.userFirstName.value} ${userFinded.userLastName.value}`,
        endpoint: envs.API_URL,
        token
      });
      emailService.sendEmail(userFinded.userEmail.value, emailTemplate, "Confirmation email");
      res.json({ message: "User already registered, please verify your account", status: 200 });
      return;
    }
    const passwordHashed = await hash(password as string, 10);
    const user = await createUserUseCase.run({ email, firstName, lastName, password: passwordHashed, isVerify: false });
    const token = jwtService.createAccessToken({ body: { user: user.userId } });
    const emailTemplate = templateService.compile("confirmationEmail", {
      name: `${user.userFirstName.value} ${user.userLastName.value}`,
      endpoint: envs.API_URL,
      token
    });
    emailService.sendEmail(user.userEmail.value, emailTemplate, "Confirmation email");
    res.json({message:"Thank you for registering, please verify your account", status:200});
  } catch (e) {
    next(e);
  }
};