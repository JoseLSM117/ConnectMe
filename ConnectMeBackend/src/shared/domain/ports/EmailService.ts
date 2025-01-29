export interface EmailService {
  sendConfirmationEmail: (to: string) => Promise<void>,
}

