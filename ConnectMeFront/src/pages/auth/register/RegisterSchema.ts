import * as yup from 'yup';

export const registerSchema = yup.object({
  firstName: yup
    .string()
    .required('First name is required')
    .min(2, 'First name must be at least 2 characters')
    .max(50, 'First name must be at most 50 characters'),

  lastName: yup
    .string()
    .required('Last name is required')
    .min(2, 'Last name must be at least 2 characters')
    .max(50, 'Last name must be at most 50 characters'),

  phoneNumber: yup
    .number()
    .required('Phone number is required')
    .min(1000, 'Phone number must have at least 4 digits')  // 1000 = 4 dígitos
    // eslint-disable-next-line max-len
    .max(999999999999999, 'Phone number must have at most 15 digits'), // 999... (15 dígitos)

  email: yup
    .string()
    .nullable()
    .notRequired()
    .default(null)
    .email('Invalid email address'),

  password: yup
    .string()
    .required('Password is required')
    .min(6, 'Password must be at least 6 characters')
    .max(100, 'Password must be at most 100 characters'),

  countryCode: yup
    .string()
    .required('Country code is required')
    .min(2, 'Country code must be at least 2 characters')
    .max(4, 'Country code must be at most 4 characters')
    // eslint-disable-next-line max-len
    .matches(/^\+[1-9]\d{0,2}$/, "Invalid country code (must start with '+' and have 1 to 3 digits)"),
});
