export interface EmailService {
  sendConfirmationEmail: (to: string, fullName: string, token:string) => void,
}

