'use client';

import Link from 'next/link';
import { useRouter } from 'next/navigation';

export default function Navbar() {
  const router = useRouter();

  const handleLogout = () => {
    localStorage.removeItem("isAdminLoggedIn");
    router.push("/admin/login");
  };
 
  return (
    <nav className="fixed top-0 w-full bg-white border-b border-gray-200 z-50 px-6 py-4">
      <div className="max-w-7xl mx-auto flex justify-between items-center">
        <Link href="/admin/dashboard" className="text-xl font-bold text-black">
          Dashboard
        </Link>
        <div className="flex gap-6 text-gray-600 font-medium">
          <Link onClick={handleLogout} href="/admin/login" className="hover:text-black">
            Logout
          </Link>
        </div>
      </div>
    </nav>
  );
}