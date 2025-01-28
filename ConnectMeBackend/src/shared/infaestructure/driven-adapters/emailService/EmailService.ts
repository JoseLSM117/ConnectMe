import type { EmailService } from "@src/shared/domain/ports/EmailService";
import nodemailer from "nodemailer";
import { EmailRejectedException, SendEmailException } from "./Exceptions";
const portMailer: number = parseInt(process.env.MAILER_PORT ?? "0");

export class EmailServiceImpl implements EmailService {
  private readonly transporter = nodemailer.createTransport({
    host: process.env.MAILER_HOST,
    port: portMailer,
    secure: false,
    auth: {
      user: process.env.MAILER_EMAIL,
      pass: process.env.MAILER_SECRET_KEY
    }
  });
  sendConfirmationEmail(to: string, fullName: string, token: string): void {
    const message = `
      <!doctype html>
      <html>
      <head>
          <meta charset="utf-8">
          <style>
              body {
                  font-family: Arial, sans-serif;
                  margin: 0;
                  padding: 0;
                  background-color: #f4f4f4;
              }
              .container {
                  width: 100%;
                  max-width: 600px;
                  margin: 0 auto;
                  background-color: #ffffff;
                  padding: 20px;
                  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                  border-radius: 8px;
              }
              h1 {
                  color: #333333;
                  font-size: 24px;
                  margin-bottom: 10px;
              }
              h2 {
                  color: #555555;
                  font-size: 18px;
                  margin-top: 0;
                  margin-bottom: 20px;
              }
              p {
                  color: #666666;
                  font-size: 14px;
                  line-height: 1.5;
                  margin-bottom: 20px;
              }
              a {
                  color: #15c;
                  border:1px solid #007bff;
                  text-decoration: none;
                  padding: 10px 20px;
                  border-radius: 5px;
                  font-size: 16px;
                  display: inline-block;
                  font-weight: bold;
              }
              a:hover{
                  background-color:#007bff;
                  color:#fff;
              }
              .footer {
                  font-size: 12px;
                  color: #999999;
                  text-align: center;
                  margin-top: 20px;
              }
          </style>
      </head>
      <body>
          <div class="container">
              <h1>Hola ${fullName}, bienvenido a ConnectMe .</h1>
              <h2>Para poder continuar, solo debes confirmar tu correo electrónico dando clic en el siguiente enlace :)</h2>
              <p>Haz clic en el botón a continuación para confirmar tu dirección de correo electrónico:</p>
              <a href='${process.env.CONFIRMATION_DOMAIN}/user/auth/verify?token=${token}'>Confirmar Correo</a>
              <div class="footer">
                  <p>Si no solicitaste este correo, por favor ignóralo.</p>
                  <p>© ${new Date().getFullYear()} CandyCrave. Todos los derechos reservados.</p>
              </div>
          </div>
      </body>
      </html>
    `;
    this.transporter.sendMail({
      from: "ConnectMe <connectme@gmail.com>",
      to,
      subject: "Email de confirmación en ConnectMe",
      html: message
    }, function (error, info) {
      if (error) {
        throw new SendEmailException();
      }
      if(info.rejected.length >= 1){
        console.log("Rejected email in shared/driven-adapters/EmailService",info.rejected.length);
        throw new EmailRejectedException();
      }
    });
  }
}