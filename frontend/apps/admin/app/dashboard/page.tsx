'use client';

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

export default function Dashboard() {
  const router = useRouter();

  const [tickets, setTickets] = useState<any[]>([]);

  useEffect(() => {
    const isLoggedIn = localStorage.getItem("isAdminLoggedIn");

    if (!isLoggedIn) {
      router.push("/admin/login");
    }
  }, []);

  const getTickets = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/admin/tickets');

      const data = await res.json();

      setTickets(data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    getTickets();
  }, []);

  const handleView = (ticketId: string) => {
    router.push(`/admin/dashboard/tickets/${ticketId}`);
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto bg-white p-8 rounded-2xl shadow-md border border-gray-200">

        {/* Header */}
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-bold text-gray-800">
            Query Tickets
          </h2>

          <span className="text-sm text-gray-500">
            Total: {tickets.length}
          </span>
        </div>

        {/* Table */}
        <div className="overflow-hidden rounded-xl border border-gray-200">
          <div className="max-h-[65vh] overflow-y-auto">
          <table className="w-full text-sm">

            {/* Head */}
            <thead className="bg-gray-50 text-gray-600 uppercase text-xs">
              <tr>
                <th className="px-4 py-3 text-left">Ticket ID</th>
                <th className="px-4 py-3 text-left">Query</th>
                <th className="px-4 py-3 text-left">Status</th>
                <th className="px-4 py-3 text-center">Action</th>
              </tr>
            </thead>

            {/* Body */}
            <tbody className="divide-y">

              {[...tickets].reverse().map((ticket) => (
                <tr key={ticket.id} className="hover:bg-gray-50 transition">

                  {/* Ticket ID */}
                  <td className="px-4 py-3 font-medium text-gray-800">
                    {ticket.ticketId}
                  </td>

                  {/* Query */}
                  <td className="px-4 py-3">
                    <textarea
                      value={ticket.query}
                      readOnly
                      rows={2}
                      className="w-full resize-none border border-gray-200 rounded-lg p-2 bg-gray-50 text-sm focus:outline-none"
                    />
                  </td>

                  {/* Status */}
                  <td className="px-4 py-3">
                    <span
                      className={`px-3 py-1 text-xs font-semibold rounded-full
                        ${
                          ticket.status === 'OPEN'
                            ? 'bg-green-100 text-green-700'
                            : ticket.status === 'IN_PROGRESS'
                            ? 'bg-yellow-100 text-yellow-700'
                            : ticket.status === 'CLOSED'
                            ? 'bg-red-100 text-red-700'
                            : 'bg-gray-100 text-gray-700'
                        }
                      `}
                    >
                      {ticket.status
                        ?.toLowerCase()
                        .replace('_', ' ')
                        .replace(/\b\w/g, (c: string) => c.toUpperCase())}
                    </span>
                  </td>

                  {/* Action */}
                  <td className="px-4 py-3 text-center">
                    <button
                      onClick={() => handleView(ticket.ticketId)}
                      className="px-4 py-2 text-sm bg-black text-white rounded-lg hover:bg-gray-700 transition"
                    >
                      View
                    </button>
                  </td>

                </tr>
              ))}

            </tbody>
          </table>
          </div>
        </div>

      </div>
    </div>
  );
}