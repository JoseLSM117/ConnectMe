import type { NextFunction, Request, Response } from "express";
import { UserPrismaRepository } from "@src/user/infraestructure/driven-adapters/UserPrismaRepository";
import { CreateUser } from "@src/user/application/usecases/CreateUser";
import { EmailTemplateService } from "@src/shared/application/services/emailService/EmailTemplateService";
import { envs } from "@src/shared/infaestructure/env/envs";
import { EmailServiceImpl } from "@src/shared/infaestructure/driven-adapters/emailService/EmailService";

export const createUser = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
  const { email, firstName, lastName, password } = req.body;
  const prismaRepository = new UserPrismaRepository();
  const createUserUseCase = new CreateUser(prismaRepository);
  const templateService = new EmailTemplateService();
  const emailService = new EmailServiceImpl({
    host: envs.MAILER_HOST,
    pass: envs.MAILER_PASSWORD,
    port: Number(envs.MAILER_PORT),
    user: envs.MAILER_EMAIL,
  });
  try {
    const user = await createUserUseCase.run({ email, firstName, lastName, password });
    const emailTemplate = templateService.compile("confirmationEmail", {
      name: `${user.firstName.value} ${user.lastName.value}`,
      endpoint: envs.API_URL,
      token: "123"
    });
    emailService.sendEmail(user.email.value, emailTemplate, "Confirmation email");
    res.json(user);
  } catch (e) {
    next(e);
  }
};