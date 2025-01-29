import { EmailServiceImpl } from "@src/shared/infaestructure/driven-adapters/emailService/EmailService"
import { SendEmailException } from "@src/shared/infaestructure/driven-adapters/emailService/Exceptions";
import nodemailer from "nodemailer";
jest.mock("nodemailer", () => ({
  createTransport: jest.fn(() => ({
    sendMail: jest.fn(),
  })),
}));
const mockConfig = {
  host: "smtp.example.com",
  port: 465,
  user: "user",
  pass: "pass",
};
describe("EmailServiceImpl", () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test("Should initialize the transporter with the correct configuration", () => {
    new EmailServiceImpl(mockConfig, "template");
    expect(nodemailer.createTransport).toHaveBeenCalledWith({
      host: mockConfig.host,
      port: mockConfig.port,
      secure: true,
      auth: {
        user: mockConfig.user,
        pass: mockConfig.pass
      }
    })
  })

  test("Should accept aditional transporter options", () => {
    const transporterOptions = { component: "test" }
    new EmailServiceImpl(mockConfig, "template", transporterOptions);

    expect(nodemailer.createTransport).toHaveBeenCalledWith({
      host: mockConfig.host,
      port: mockConfig.port,
      secure: true,
      auth: {
        user: mockConfig.user,
        pass: mockConfig.pass,
      },
      ...transporterOptions,
    });
  })
})


describe("sendConfirmationEmail", () => {
  const template = "<html>Confirmar cuenta</html>";
  let emailService: EmailServiceImpl;
  let mockTransporter: { sendMail: jest.Mock };

  beforeEach(() => {
    // Crear un mock de transporter con sendMail
    mockTransporter = {
      sendMail: jest.fn().mockResolvedValue({}),
    };

    // Mockear createTransport para devolver el mockTransporter
    (nodemailer.createTransport as jest.Mock).mockReturnValue(mockTransporter);

    emailService = new EmailServiceImpl(
      { host: "smtp.test.com", port: 587, user: "user", pass: "pass" },
      template
    );
  });

  test("debería enviar el correo con parámetros correctos", async () => {
    const to = "test@example.com";

    await emailService.sendConfirmationEmail(to);

    expect(mockTransporter.sendMail).toHaveBeenCalledWith({
      to,
      subject: "Confirma tu cuenta",
      html: template,
    });
  });

  test("debería lanzar SendEmailException en caso de error", async () => {
    const mockError = new Error()
    mockTransporter.sendMail.mockRejectedValue(mockError);
    await expect(emailService.sendConfirmationEmail("")).rejects.toThrow(SendEmailException)

    try {
      await emailService.sendConfirmationEmail("test@example.com");
    } catch (error) {
      if (error instanceof SendEmailException) {
        const sendEmailError = error as SendEmailException;
        expect(sendEmailError.message).toBe("Confirmation email failed");
      } else {
        throw error;
      }
    }
  });
});