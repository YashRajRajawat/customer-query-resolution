import Link from 'next/link';

export default function Navbar() {
  return (
    <nav className="fixed top-0 w-full bg-white border-b border-gray-200 z-50 px-6 py-4">
      <div className="max-w-7xl mx-auto flex justify-between items-center">
        <Link href="/" className="text-xl font-bold text-black">
          CREATE QUERY
        </Link>
        <div className="flex gap-6 text-gray-600 font-medium">
          <Link href="/about" className="hover:text-black">About</Link>
          <Link href="/ticket-status" className="hover:text-black">Ticket Status</Link>
        </div>
      </div>
    </nav>
  );
}