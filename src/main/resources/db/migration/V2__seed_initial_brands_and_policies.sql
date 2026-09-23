-- Flyway Migration V2: Seed 2 Brands, Policies (Return, Refund, Shipping, Cancellation), and Scenario Data

-- 1. Insert Brands
INSERT INTO brands (id, name, code, description, tone_guidelines, created_at, updated_at)
VALUES 
(1, 'Aura Skincare', 'AURA', 'Premium organic skincare and beauty brand specializing in glass-bottled serums and botanical treatments.', 'Empathetic, soothing, polite, and reassuring. Always express genuine care for customer well-being.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Apex Electronics', 'APEX', 'High-performance audio equipment, noise-canceling headphones, and digital sound accessories.', 'Professional, concise, technical, and solution-driven. Provide clear step-by-step instructions.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

-- Reset sequence for brands
SELECT setval('brands_id_seq', (SELECT MAX(id) FROM brands));

-- 2. Insert Policies for Brand 1 (Aura Skincare)
INSERT INTO knowledge_articles (brand_id, category, title, content, keywords, active, created_at, updated_at)
VALUES
(1, 'RETURN', 'Aura Skincare 7-Day Return Policy', 'Customers may initiate returns within 7 calendar days of confirmed delivery. Due to sanitary and hygiene standards for cosmetics, products that have been unsealed and used beyond initial testing cannot be returned unless they arrived defective or damaged.', 'return, return window, 7 days, open product, sanitary', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'REFUND', 'Aura Skincare Damaged Item & Refund Policy', 'Refunds are permitted only within 7 days of delivery. For items that arrive broken or damaged (such as broken glass bottles or leaky droppers), customers must provide a photo of the damaged package and product. Once the photo is verified, Aura provides an immediate free replacement or 100% full refund with no return shipment required. After 7 days from delivery, refunds are strictly not permitted.', 'refund, broken, damaged bottle, glass broken, replacement, photo proof, 7 days', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'SHIPPING', 'Aura Skincare Shipping & Delivery Timeline', 'Standard domestic shipping takes 3 to 5 business days. Express overnight shipping takes 1-2 business days. Tracking numbers are emailed within 24 hours of order dispatch.', 'shipping, delivery, tracking, express, carrier, delay', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'CANCELLATION', 'Aura Skincare Order Cancellation Policy', 'Orders can be cancelled within 1 hour of placement through the order confirmation page. Once an order is transferred to fulfillment packing, it cannot be cancelled and must follow the standard return process.', 'cancel, cancellation, cancel order, 1 hour, warehouse', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 3. Insert Policies for Brand 2 (Apex Electronics)
INSERT INTO knowledge_articles (brand_id, category, title, content, keywords, active, created_at, updated_at)
VALUES
(2, 'RETURN', 'Apex Electronics 30-Day Return Policy', 'Apex provides a 30-day return window from the date of confirmed delivery. Products must include all original packaging, cables, documentation, and warranty cards. A 15% restocking fee applies to non-defective returned goods that have been opened.', 'return, 30 days, restocking fee, original packaging, cables', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'REFUND', 'Apex Electronics Refund & Hardware Inspection Process', 'Refunds are processed within 7-10 business days after the returned product is received and inspected at our hardware diagnostic facility. If hardware arrived damaged or DOA (dead on arrival), return shipping is covered via prepaid label. Refund requests submitted after 30 days are rejected.', 'refund, inspection, 30 days, defective, diagnostic, prepaid label', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'SHIPPING', 'Apex Electronics Secure Freight Shipping Policy', 'All audio hardware is shipped via insured courier with signature required upon delivery. Shipping transit takes 2-4 business days.', 'shipping, signature, courier, insured, freight', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'CANCELLATION', 'Apex Electronics Cancellation Window', 'Orders may be cancelled any time before tracking information is generated. Once the carrier barcode is scanned, the package cannot be recalled.', 'cancel, cancellation, dispatch, tracking generated', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 4. Insert Seed Customers
INSERT INTO customers (id, name, email, phone, created_at)
VALUES
(1, 'Sarah Jenkins', 'sarah.jenkins@example.com', '+1-555-0192', CURRENT_TIMESTAMP),
(2, 'David Chen', 'david.chen@example.com', '+1-555-0148', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

SELECT setval('customers_id_seq', (SELECT MAX(id) FROM customers));

-- 5. Insert Seed Orders
INSERT INTO orders (id, order_number, customer_id, brand_id, item_summary, order_status, order_date, delivery_date, total_amount, currency, created_at)
VALUES
(1, 'ORD-AURA-8921', 1, 1, 'Hydrating Glow Serum 50ml (Glass Dropper Bottle)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 48.00, 'USD', CURRENT_TIMESTAMP),
(2, 'ORD-APEX-4019', 2, 2, 'Apex Pro ANC Studio Headphones (Black)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '10 days', CURRENT_TIMESTAMP - INTERVAL '8 days', 299.00, 'USD', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

SELECT setval('orders_id_seq', (SELECT MAX(id) FROM orders));

-- 6. Insert Initial Conversation for Assessment Scenario
INSERT INTO conversations (id, brand_id, customer_id, order_id, channel, status, created_at, updated_at)
VALUES
(1, 1, 1, 1, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

SELECT setval('conversations_id_seq', (SELECT MAX(id) FROM conversations));

-- 7. Insert Initial Customer Message matching the scenario prompt
INSERT INTO messages (id, conversation_id, sender_type, sender_name, content, created_at)
VALUES
(1, 1, 'CUSTOMER', 'Sarah Jenkins', 'My order was delivered but the bottle is broken. What can I do?', CURRENT_TIMESTAMP - INTERVAL '1 hour')
ON CONFLICT (id) DO NOTHING;

SELECT setval('messages_id_seq', (SELECT MAX(id) FROM messages));
