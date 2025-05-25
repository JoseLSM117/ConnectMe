import { InputText } from 'primereact/inputtext';
import { InputNumber } from 'primereact/inputnumber';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';

import registerStyles from './register.module.css';
import { Controller, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { registerSchema } from './RegisterSchema';
import { useNavigate } from 'react-router';
import { Dropdown } from 'primereact/dropdown';
interface registerUserProps {
  firstName: string;
  lastName: string;
  phoneNumber: number;
  email: string | null;
  password: string;
  countryCode: string;
}
export const Register = () => {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<registerUserProps>({
    resolver: yupResolver(registerSchema),
  });
  const onSubmit = handleSubmit((data) => {
    console.log('On submit', data);
  });
  console.log('Errors', {
    errors,
  });

  const countries = [
    { name: 'United States +1', code: 'US' }, // Código de llamada
    { name: '🇲🇽 Mexico +52', code: '+52' },
    { name: 'Frostholm +999', code: '+999' }, // Ejemplo ficticio
  ];

  return (
    <div className={registerStyles['register-container']}>
      <div>
        <h2>Register New Account xd</h2>
      </div>
      <Card>
        <form className={registerStyles['register-form']} onSubmit={onSubmit}>
          <div className={registerStyles['register-input']}>
            <InputText
              id='first-name'
              aria-errormessage='first-name-error'
              aria-describedby='first-name-desc'
              placeholder='First Name *'
              {...register('firstName')}
              invalid={errors.firstName ? true : false}
            />
            {errors.firstName !== undefined ? (
              <small
                className={registerStyles['input-error']}
                id='first-name-error'
              >
                {errors.firstName.message}
              </small>
            ) : (
              <small
                className={registerStyles['input-desc']}
                id='first-name-desc'
              >
                First name required
              </small>
            )}
          </div>
          <div className={registerStyles['register-input']}>
            <InputText
              placeholder='Last Name *'
              id='last-name'
              aria-errormessage='last-name-error'
              aria-describedby='last-name-desc'
              invalid={errors.lastName ? true : false}
              {...register('lastName')}
            />
            {errors.lastName !== undefined ? (
              <small
                className={registerStyles['input-error']}
                id='last-name-error'
              >
                {errors.lastName.message}
              </small>
            ) : (
              <small
                className={registerStyles['input-desc']}
                id='last-name-desc'
              >
                Last name required
              </small>
            )}
          </div>
          <div className={registerStyles['register-input']}>
            <div className={registerStyles['phone-number-container']}>
              <Controller
                name='countryCode'
                control={control}
                render={({ field }) => (
                  <Dropdown
                    id='country-code'
                    aria-errormessage='country-code-error'
                    aria-describedby='country-code-desc'
                    placeholder='+XX'
                    options={countries}
                    optionLabel='name'
                    value={field.value}
                    valueTemplate={(option, props) =>
                      option ? option.code : props.placeholder
                    }
                    onChange={(e) => field.onChange(e.value)}
                    onBlur={field.onBlur}
                    ref={field.ref}
                    className={registerStyles['country-code']}
                  />
                )}
              />
              <Controller
                name='phoneNumber'
                control={control}
                render={({ field }) => (
                  <InputNumber
                    placeholder='Phone Number *'
                    useGrouping={false}
                    id='phone-number'
                    aria-errormessage='phone-number-error'
                    aria-describedby='phone-number-error'
                    invalid={errors.phoneNumber ? true : false}
                    onValueChange={(e) => field.onChange(e.value)}
                    onBlur={field.onBlur}
                    ref={field.ref}
                    className={registerStyles['phone-number']}
                  />
                )}
              />
            </div>
            {errors.phoneNumber !== undefined ? (
              <small
                className={registerStyles['input-error']}
                id='phone-number-error'
              >
                {errors.phoneNumber.message}
              </small>
            ) : (
              <small
                className={registerStyles['input-desc']}
                id='phone-number-desc'
              >
                Phone number is required
              </small>
            )}
          </div>
          <InputText placeholder='Password *' />
          <InputText placeholder='Email' />
          <Button label='Register' type='submit' />
          <Button
            label='Sign In'
            outlined
            severity='secondary'
            onClick={() => navigate('/auth/login')}
          />
        </form>
      </Card>
    </div>
  );
};
