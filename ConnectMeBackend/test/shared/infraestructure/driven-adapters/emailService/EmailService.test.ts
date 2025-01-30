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
    new EmailServiceImpl(mockConfig);
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
    new EmailServiceImpl(mockConfig, transporterOptions);

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

describe("SendEmail", () => {
  let emailService: EmailServiceImpl;
  let mockTransporter: { sendMail: jest.Mock };

  beforeEach(() => {
    mockTransporter = {
      sendMail: jest.fn().mockResolvedValue({})
    };
    (nodemailer.createTransport as jest.Mock).mockReturnValue(mockTransporter);
    emailService = new EmailServiceImpl({ host: "smtp.test.com", port: 587, user: "user", pass: "pass" });
  })

  test("Should send the email with correctly params", () => {
    const to = "test@example.com";
    const html = "<h1>test</h1>";
    const subject = "test email";
    emailService.sendEmail(to, html, subject);
    expect(mockTransporter.sendMail).toHaveBeenCalledWith({ to, html, subject });
  })

  test("Should throw SendEmailException", async() => {
    const to = "test@example.com";
    const html = "<h1>test</h1>";
    const subject = "test email";
    const mockError = new Error()
    mockTransporter.sendMail.mockRejectedValue(mockError);
    await expect(emailService.sendEmail(to, html, subject)).rejects.toThrow()
  })
})