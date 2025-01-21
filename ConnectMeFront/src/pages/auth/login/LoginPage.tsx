import TextsmsIcon from '@mui/icons-material/Textsms';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import { TextField } from '@mui/material';
import { Link } from 'react-router-dom';


export const LoginPage = () => {
    return (
        <div className='space-y-5'>
            <div className='flex gap-2 items-center justify-center'>
                <TextsmsIcon className='text-blue-400' />
                <h1 className='text-2xl font-semibold'>ConnectMe</h1>
            </div>
            <Paper elevation={0} className='sm:min-w-[400px] flex flex-col gap-4 p-4 py-8'>
                <h2 className='text-lg font-medium'>Login</h2>
                <form action="" className='space-y-6'>
                    <div>
                        <TextField className='w-full' id="email" label="Your email address" variant="outlined" />
                    </div>
                    <div>
                        <TextField className='w-full' id="password" type='password' label="Your password" variant="outlined" />
                    </div>
                    <div>
                        <Link to={"/auth/register"} className='text-blue-400 font-semibold'>
                            ¿You haven't account? Register
                        </Link>
                    </div>
                    <Button variant='contained' className='w-full'>Account Login</Button>
                </form>
            </Paper>
        </div>
    )
}
