import { Outlet } from 'react-router';
import authStyles from './auth.module.css';
export const AuthLayout = () => (
  <div className={authStyles['authLayout']}>
    <div>
      <img src="data:image/svg+xml,%3csvg%20xmlns='http://www.w3.org/2000/svg'%20width='68'%20height='37'%20viewBox='0%200%2068%2037'%3e%3cg%20id='Group_1'%20data-name='Group%201'%20transform='translate(-44%20-34)'%3e%3crect%20id='Rectangle_10'%20data-name='Rectangle%2010'%20width='37'%20height='37'%20rx='18.5'%20transform='translate(75%2034)'%20fill='%23e1edf7'/%3e%3crect%20id='Rectangle_9'%20data-name='Rectangle%209'%20width='37'%20height='37'%20rx='18.5'%20transform='translate(44%2034)'%20fill='%233a8df5'/%3e%3c/g%3e%3c/svg%3e" alt="Logo" />
    </div>
    <Outlet />
  </div>
);
