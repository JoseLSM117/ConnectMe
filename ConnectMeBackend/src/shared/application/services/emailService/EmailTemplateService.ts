import path from "path";
import fs from "fs";
export class EmailTemplateService {
  private readonly templates: Record<string, string> = {};

  constructor() {
    this.templates.confirmationEmail = fs.readFileSync(
      path.resolve(__dirname, 'templates/confirmationEmail.html'),
      'utf-8'
    );
  }

  compile(templateName: string, data: Record<string, string>): string {
    const template = this.templates[templateName];
    if (!template) {
      throw new Error(`Template ${templateName} not found`);
    }
    return Object.entries(data).reduce((acc, [key, value]) => acc.replace(new RegExp(`\\\${${key}}`, 'g'), value), template);
  }
}