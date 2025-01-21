import { Link } from 'react-router-dom';

import { useForm, SubmitHandler } from 'react-hook-form'

import TextField from '@mui/material/TextField';
import TextsmsIcon from '@mui/icons-material/Textsms';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';

type RegisterInputs = {
    email: string
    password: string
    firstName: string
    lastName: string
}
export const RegisterPage = () => {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<RegisterInputs>()
    const onSubmit: SubmitHandler<RegisterInputs> = (data) => {
        console.log(data);
    }

    return (
        <div className='space-y-5 px-4'>
            <div className='flex gap-2 items-center justify-center'>
                <TextsmsIcon className='text-blue-400' />
                <h1 className='text-2xl font-semibold'>ConnectMe</h1>
            </div>
            <Paper elevation={0} className='sm:w-[600px] w-[340px] flex flex-col gap-4 px-4 py-8'>
                <h2 className='text-lg font-medium'>Create Account</h2>
                <form action='' className='space-y-6' onSubmit={handleSubmit(onSubmit)}>
                    <div className='grid sm:grid-cols-2 gap-6'>
                        <div className='flex flex-col gap-2'>
                            <TextField {...register('email', {
                                required: { value: true, message: "Email is required" },
                                pattern: { value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, message: "Email is not valid" }
                            })} className='w-full' id='email' label='Your email address' variant='outlined' />
                            {errors.email && <span className='bg-red-600 text-white text-center rounded-sm'>{errors.email.message}</span>}
                        </div>
                        <div className='flex flex-col gap-2'>
                            <TextField {...register('password', {
                                required: { value: true, message: "Password is required" },
                                minLength: { value: 6, message: "Password should have at least 6 characters" }
                            })} className='w-full' id='password' type='password' label='Your password' variant='outlined' />
                            {errors.password && <span className='bg-red-600 text-white text-center rounded-sm'>{errors.password.message}</span>}
                        </div>
                        <div className='flex flex-col gap-2'>
                            <TextField {...register('firstName', { required: { value: true, message: "First name is required" } })} className='w-full' id='firstName' type='text' label='Your first name' variant='outlined' />
                            {errors.firstName && <span className='bg-red-600 text-white text-center rounded-sm'>{errors.firstName.message}</span>}
                        </div>
                        <div className='flex flex-col gap-2'>
                            <TextField {...register('lastName', { required: { value: true, message: "Last name is required" } })} className='w-full' id='lastName' type='text' label='Your last name' variant='outlined' />
                            {errors.lastName && <span className='bg-red-600 text-white text-center rounded-sm'>{errors.lastName.message}</span>}
                        </div>
                    </div>
                    <div>
                        <Link to={'/auth/login'} className='text-blue-400 font-semibold'>
                            ¿You have account? Login
                        </Link>
                    </div>
                    <Button variant='contained' type='submit' className='w-full'>Account Login</Button>
                </form>
            </Paper>
        </div>
    )
}
