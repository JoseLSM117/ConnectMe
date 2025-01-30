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
  async sendEmail(to: string, html: string, subject: string): Promise<void> {
    try {
      await this.transporter.sendMail({ to, subject, html });
    } catch (error) {
      throw new SendEmailException('transport', `Senting email failed`);
    }
  }
}