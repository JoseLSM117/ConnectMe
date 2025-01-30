export interface EmailService {
  sendEmail:(to:string, html:string, subject:string) => Promise<void>
}

