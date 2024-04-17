SELECT BillingCountry, SUM(Total) AS TotalSales FROM invoices GROUP BY BillingCountry;

SELECT STRFTIME('%Y', InvoiceDate) AS Year, SUM(Total) AS TotalSales FROM invoices GROUP BY Year;

SELECT BillingState, SUM(Total) AS TotalSales FROM invoices WHERE BillingState IS NOT NULL AND BillingCountry = 'USA' AND InvoiceDate > '2010-01-01' GROUP BY BillingState;

SELECT BillingCountry, MAX(Total) AS MaxOrderAmount FROM invoices WHERE BillingCountry = 'Germany' OR BillingCountry = 'France' GROUP BY BillingCountry;