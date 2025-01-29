import type { EmailService } from "@src/shared/domain/ports/EmailService";
import nodemailer, { type TransportOptions, type Transporter } from "nodemailer";
import { SendEmailException } from "./Exceptions";
interface EmailConfig {
  host: string;
  port: number;
  user: string;
  pass: string;
};

export class EmailServiceImpl implements EmailService {
  private readonly transporter: Transporter;

  constructor(
    private readonly config: EmailConfig,
    private readonly template: string,
    transporterOptions?: TransportOptions
  ) {
    this.transporter = nodemailer.createTransport({
      host: config.host,
      port: config.port,
      secure: config.port === 465,
      auth: {
        user: config.user,
        pass: config.pass,
      },
      ...transporterOptions,
    });
  }

  async sendConfirmationEmail(to: string): Promise<void> {
    try {
      await this.transporter.sendMail({
        to,
        subject: 'Confirma tu cuenta',
        html: this.template
      });
    } catch (error) {
      throw new SendEmailException('transport', `Confirmation email failed`);
    }
  }
}