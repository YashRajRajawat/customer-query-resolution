import Navbar from "./components/navbar";

export default function UserLayout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <Navbar />
      <main className="pt-15">  {/* Adjust based on navbar height */}
        {children}
      </main>
    </>
  );
}