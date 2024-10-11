'use client'; // Ensure this is a client component

import { useSearchParams } from 'next/navigation'; // To access query parameters
import { useEffect, useState, Suspense } from 'react';

// Component that handles payment failure
function FailPageContent() {
  const searchParams = useSearchParams(); // Access query parameters
  const orderId = searchParams?.get('orderId') || ''; // Get orderId (optional if provided in failure)
  const [errorDetails, setErrorDetails] = useState<string | null>(null); // Error details from query
  const [showSupport, setShowSupport] = useState(false); // Toggle for showing support details

  useEffect(() => {
    const errorDescription = searchParams?.get('message') || 'Unknown error'; // Capture any error message from URL
    setErrorDetails(errorDescription);
  }, [searchParams]);

  const handleRetryClick = () => {
    // Add logic to retry the payment, or redirect the user back to the payment page
    window.location.href = '/contract'; // Example: redirecting the user to the contract/payment page
  };

  return (
    <div style={{ textAlign: 'center', padding: '50px' }}>
      <h1>Payment Failed</h1>
      {orderId && <p>Order ID: {orderId}</p>}
      <p>We’re sorry, but your payment couldn’t be processed.</p>
      <p>Error: {errorDetails}</p>
      
      <button
        onClick={handleRetryClick}
        style={{
          marginTop: '20px',
          padding: '10px 20px',
          backgroundColor: '#f44336',
          color: '#fff',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer',
        }}
      >
        Retry Payment
      </button>

      <button
        onClick={() => setShowSupport(!showSupport)}
        style={{
          marginTop: '20px',
          padding: '10px 20px',
          backgroundColor: '#2196F3',
          color: '#fff',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer',
        }}
      >
        Contact Support
      </button>

      {showSupport && (
        <div style={{ marginTop: '20px', textAlign: 'left' }}>
          <p>If you continue experiencing issues, please contact our support team:</p>
          <ul>
            <li>Email: support@example.com</li>
            <li>Phone: +1-800-123-4567</li>
          </ul>
        </div>
      )}
    </div>
  );
}

// Page Component wrapped in Suspense to handle async operations
export default function FailPage() {
  return (
    <Suspense fallback={<p>Loading...</p>}>
      <FailPageContent />
    </Suspense>
  );
}
