'use client';

import * as React from 'react'
import { use, useEffect } from 'react';
import { useRouter } from "next/navigation";

export default function TicketPage({ params }){

  const router = useRouter();

  const { ticketid } = React.use(params) as { ticketid: string }; 

  const [ticketDetails, setTicketDetails] = React.useState<any>(null);

  const getTicketDetails = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api/admin/tickets/${ticketid}`);
      const data = await res.json();
      setTicketDetails(data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    getTicketDetails();
  }, []);

  const handleResolve = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api/admin/tickets/${ticketid}/resolve`, {
        method: "POST"
      });
      if (res.ok) {
        alert("Ticket marked as resolved!");
        getTicketDetails(); // Refresh details to show updated status
      } else {
        alert("Error resolving ticket");
      }
    } catch (err) {
      console.error(err);
      alert("Server error");
    }
  };


  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-4xl mx-auto bg-white p-8 rounded-2xl shadow-md border border-gray-200">

        {/* Header */}
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-bold text-gray-800">
            <button onClick={() => router.back()} className="text-sm text-gray-500 hover:underline mr-6">
              ← Back
            </button>
            Ticket Details
          </h2>

          <span className="text-sm bg-gray-900 text-white px-4 py-1 rounded-full">
            {ticketid}
          </span>
        </div>

        {/* Divider */}
        <div className="border-t mb-6"></div>

        {/* Query */}
        <div className="mb-6">
          <p className="text-sm text-gray-500 mb-1">Query</p>

          <div className="bg-gray-50 border rounded-lg p-4 text-gray-800 leading-relaxed">
            {ticketDetails?.query || 'Loading...'}
          </div>
        </div>

        {/* Status + Email Row */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

          {/* Status */}
          <div>
            <p className="text-sm text-gray-500 mb-1">Status</p>

            <span
              className={`inline-block px-3 py-1 text-sm font-medium rounded-full
                ${
                  ticketDetails?.status === 'OPEN'
                    ? 'bg-green-100 text-green-700'
                    : ticketDetails?.status === 'IN_PROGRESS'
                    ? 'bg-yellow-100 text-yellow-700'
                    : ticketDetails?.status === 'CLOSED'
                    ? 'bg-red-100 text-red-700'
                    : 'bg-gray-100 text-gray-700'
                }
              `}
            >
              {ticketDetails?.status
                ? ticketDetails.status
                    .toLowerCase()
                    .replace('_', ' ')
                    .replace(/\b\w/g, (c: string) => c.toUpperCase())
                : 'Loading...'}
            </span>
          </div>

          {/* Customer Email */}
          <div>
            <p className="text-sm text-gray-500 mb-1">Customer Email</p>

            <div className="bg-gray-50 border rounded-lg p-3 text-gray-800">
              {ticketDetails?.customer?.email || 'Loading...'}
            </div>
          </div>

        </div>
        {/* Action Buttons */}
        <button className="mt-6 px-5 py-2 bg-black text-white rounded-lg hover:bg-gray-700" onClick={handleResolve}>
          Mark as Resolved
        </button>
      </div>
    </div>
  );
}