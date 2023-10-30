"use client"

import Link from 'next/link';
import { HomeIcon, ArticleIcon, UsersIcon, VideosIcon } from './icons';
import {GiHamburgerMenu} from 'react-icons/gi'
import { useState } from 'react';

const menuItems = [
  { id: 1, label: 'Home', icon: HomeIcon, link: '/' },
  { id: 2, label: 'Configurar menú', icon: ArticleIcon, link: '/dashboard/product' },
  { id: 3, label: 'Manage Users', icon: UsersIcon, link: '/' },
  { id: 4, label: 'Manage Tutorials', icon: VideosIcon, link: '/' },
];

const Sidebar = () => {
  const [isActive, setIsActive] = useState(true);

  const handleClick = () => {
    setIsActive(!isActive);
    console.log(isActive);
  }

  return (
    <div className='h-full flex flex-col items-start bg-slate-900 border border-slate-700 rounded overflow-y-auto'>
      
      <div className="w-full flex flex-col items-end pt-5 pr-5 text-4xl">
        <GiHamburgerMenu onClick={handleClick}/>
      </div>
      {menuItems.map(({ icon: Icon, ...menu }, index) => {
        return (
          <div key={index} className=''>
            <Link href={menu.link}>
              <div className='flex py-4 px-5 items-center w-full h-full'>
                <div className="flex gap-5">
                  <Icon />
                  <p>{menu.label}</p>
                </div>
              </div>
            </Link>
          </div>
        );
      })}
    </div>
  );
};

export default Sidebar;