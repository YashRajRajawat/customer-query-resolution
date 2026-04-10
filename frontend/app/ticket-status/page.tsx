export default function TicketStatus() {
    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
            <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-lg">
                <h2 className="text-2xl font-bold text-center mb-6">
                    Ticket Status
                </h2>
                <p className="text-gray-700 text-center">
                    Your ticket is currently being processed. We will update you with the resolution as soon as possible. Thank you for your patience!
                </p>
            </div>
        </div>
    );
}