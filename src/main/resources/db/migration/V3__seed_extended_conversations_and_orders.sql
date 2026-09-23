-- Flyway Migration V3: Seed 50 Diverse Customers, Orders, Conversations, and Messages

-- 1. Insert 50 Seed Customers (IDs 3 to 52)
INSERT INTO customers (id, name, email, phone, created_at) VALUES
(3, 'Michael Brown', 'michael.brown@example.com', '+1-555-0103', CURRENT_TIMESTAMP - INTERVAL '45 days'),
(4, 'Emily Davis', 'emily.davis@example.com', '+1-555-0104', CURRENT_TIMESTAMP - INTERVAL '44 days'),
(5, 'James Wilson', 'james.wilson@example.com', '+1-555-0105', CURRENT_TIMESTAMP - INTERVAL '43 days'),
(6, 'Jessica Taylor', 'jessica.taylor@example.com', '+1-555-0106', CURRENT_TIMESTAMP - INTERVAL '42 days'),
(7, 'Daniel Martinez', 'daniel.martinez@example.com', '+1-555-0107', CURRENT_TIMESTAMP - INTERVAL '41 days'),
(8, 'Sophia Anderson', 'sophia.anderson@example.com', '+1-555-0108', CURRENT_TIMESTAMP - INTERVAL '40 days'),
(9, 'Matthew Thomas', 'matthew.thomas@example.com', '+1-555-0109', CURRENT_TIMESTAMP - INTERVAL '39 days'),
(10, 'Olivia Jackson', 'olivia.jackson@example.com', '+1-555-0110', CURRENT_TIMESTAMP - INTERVAL '38 days'),
(11, 'Ethan White', 'ethan.white@example.com', '+1-555-0111', CURRENT_TIMESTAMP - INTERVAL '37 days'),
(12, 'Ava Harris', 'ava.harris@example.com', '+1-555-0112', CURRENT_TIMESTAMP - INTERVAL '36 days'),
(13, 'Alexander Martin', 'alexander.martin@example.com', '+1-555-0113', CURRENT_TIMESTAMP - INTERVAL '35 days'),
(14, 'Isabella Thompson', 'isabella.thompson@example.com', '+1-555-0114', CURRENT_TIMESTAMP - INTERVAL '34 days'),
(15, 'William Garcia', 'william.garcia@example.com', '+1-555-0115', CURRENT_TIMESTAMP - INTERVAL '33 days'),
(16, 'Mia Martinez', 'mia.martinez@example.com', '+1-555-0116', CURRENT_TIMESTAMP - INTERVAL '32 days'),
(17, 'Benjamin Robinson', 'benjamin.robinson@example.com', '+1-555-0117', CURRENT_TIMESTAMP - INTERVAL '31 days'),
(18, 'Charlotte Clark', 'charlotte.clark@example.com', '+1-555-0118', CURRENT_TIMESTAMP - INTERVAL '30 days'),
(19, 'Lucas Rodriguez', 'lucas.rodriguez@example.com', '+1-555-0119', CURRENT_TIMESTAMP - INTERVAL '29 days'),
(20, 'Amelia Lewis', 'amelia.lewis@example.com', '+1-555-0120', CURRENT_TIMESTAMP - INTERVAL '28 days'),
(21, 'Henry Lee', 'henry.lee@example.com', '+1-555-0121', CURRENT_TIMESTAMP - INTERVAL '27 days'),
(22, 'Harper Walker', 'harper.walker@example.com', '+1-555-0122', CURRENT_TIMESTAMP - INTERVAL '26 days'),
(23, 'Sebastian Hall', 'sebastian.hall@example.com', '+1-555-0123', CURRENT_TIMESTAMP - INTERVAL '25 days'),
(24, 'Evelyn Allen', 'evelyn.allen@example.com', '+1-555-0124', CURRENT_TIMESTAMP - INTERVAL '24 days'),
(25, 'Jack Young', 'jack.young@example.com', '+1-555-0125', CURRENT_TIMESTAMP - INTERVAL '23 days'),
(26, 'Abigail Hernandez', 'abigail.hernandez@example.com', '+1-555-0126', CURRENT_TIMESTAMP - INTERVAL '22 days'),
(27, 'Owen King', 'owen.king@example.com', '+1-555-0127', CURRENT_TIMESTAMP - INTERVAL '21 days'),
(28, 'Emily Wright', 'emily.wright@example.com', '+1-555-0128', CURRENT_TIMESTAMP - INTERVAL '20 days'),
(29, 'Samuel Lopez', 'samuel.lopez@example.com', '+1-555-0129', CURRENT_TIMESTAMP - INTERVAL '19 days'),
(30, 'Elizabeth Hill', 'elizabeth.hill@example.com', '+1-555-0130', CURRENT_TIMESTAMP - INTERVAL '18 days'),
(31, 'Leo Scott', 'leo.scott@example.com', '+1-555-0131', CURRENT_TIMESTAMP - INTERVAL '17 days'),
(32, 'Ella Green', 'ella.green@example.com', '+1-555-0132', CURRENT_TIMESTAMP - INTERVAL '16 days'),
(33, 'Julian Adams', 'julian.adams@example.com', '+1-555-0133', CURRENT_TIMESTAMP - INTERVAL '15 days'),
(34, 'Avery Baker', 'avery.baker@example.com', '+1-555-0134', CURRENT_TIMESTAMP - INTERVAL '14 days'),
(35, 'Gabriel Gonzalez', 'gabriel.gonzalez@example.com', '+1-555-0135', CURRENT_TIMESTAMP - INTERVAL '13 days'),
(36, 'Scarlett Nelson', 'scarlett.nelson@example.com', '+1-555-0136', CURRENT_TIMESTAMP - INTERVAL '12 days'),
(37, 'Carter Carter', 'carter.carter@example.com', '+1-555-0137', CURRENT_TIMESTAMP - INTERVAL '11 days'),
(38, 'Grace Mitchell', 'grace.mitchell@example.com', '+1-555-0138', CURRENT_TIMESTAMP - INTERVAL '10 days'),
(39, 'Jayden Perez', 'jayden.perez@example.com', '+1-555-0139', CURRENT_TIMESTAMP - INTERVAL '9 days'),
(40, 'Chloe Roberts', 'chloe.roberts@example.com', '+1-555-0140', CURRENT_TIMESTAMP - INTERVAL '8 days'),
(41, 'Luke Turner', 'luke.turner@example.com', '+1-555-0141', CURRENT_TIMESTAMP - INTERVAL '7 days'),
(42, 'Victoria Phillips', 'victoria.phillips@example.com', '+1-555-0142', CURRENT_TIMESTAMP - INTERVAL '6 days'),
(43, 'Anthony Campbell', 'anthony.campbell@example.com', '+1-555-0143', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(44, 'Riley Parker', 'riley.parker@example.com', '+1-555-0144', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(45, 'Isaac Evans', 'isaac.evans@example.com', '+1-555-0145', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(46, 'Aria Edwards', 'aria.edwards@example.com', '+1-555-0146', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(47, 'Dylan Collins', 'dylan.collins@example.com', '+1-555-0147', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(48, 'Zoey Stewart', 'zoey.stewart@example.com', '+1-555-0148', CURRENT_TIMESTAMP - INTERVAL '12 hours'),
(49, 'Nathan Sanchez', 'nathan.sanchez@example.com', '+1-555-0149', CURRENT_TIMESTAMP - INTERVAL '8 hours'),
(50, 'Penelope Morris', 'penelope.morris@example.com', '+1-555-0150', CURRENT_TIMESTAMP - INTERVAL '6 hours'),
(51, 'Caleb Rogers', 'caleb.rogers@example.com', '+1-555-0151', CURRENT_TIMESTAMP - INTERVAL '4 hours'),
(52, 'Layla Reed', 'layla.reed@example.com', '+1-555-0152', CURRENT_TIMESTAMP - INTERVAL '2 hours')
ON CONFLICT (id) DO NOTHING;

-- 2. Insert 50 Seed Orders (IDs 3 to 52)
INSERT INTO orders (id, order_number, customer_id, brand_id, item_summary, order_status, order_date, delivery_date, total_amount, currency, created_at) VALUES
-- Aura Skincare orders (Brand 1)
(3, 'ORD-AURA-1003', 3, 1, 'Radiant Vitamin C Glow Drops 30ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '5 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 52.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(4, 'ORD-AURA-1004', 4, 1, 'Midnight Recovery Botanical Facial Oil 50ml', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '3 days', NULL, 64.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(5, 'ORD-AURA-1005', 5, 1, 'Soothing Rosewater Hydrating Toner 120ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '6 days', CURRENT_TIMESTAMP - INTERVAL '3 days', 34.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '6 days'),
(6, 'ORD-AURA-1006', 6, 1, 'Gentle Foaming Oat Cleanser 150ml', 'PROCESSING', CURRENT_TIMESTAMP - INTERVAL '1 day', NULL, 28.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(7, 'ORD-AURA-1007', 7, 1, 'Barrier Restore Ceramide Moisturizer 60g', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '12 days', CURRENT_TIMESTAMP - INTERVAL '9 days', 58.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '12 days'),
(8, 'ORD-AURA-1008', 8, 1, 'Illuminating Peptide Eye Cream 15ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '20 days', CURRENT_TIMESTAMP - INTERVAL '16 days', 42.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '20 days'),
(9, 'ORD-AURA-1009', 9, 1, 'Deep Clarifying French Green Clay Mask 100g', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 36.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(10, 'ORD-AURA-1010', 10, 1, 'Sun Shield Mineral SPF 50 Face Sunscreen 75ml', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '2 days', NULL, 38.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(11, 'ORD-AURA-1011', 11, 1, 'Hydrating Lip Therapy Mask 15g (Rose)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '18 days', CURRENT_TIMESTAMP - INTERVAL '15 days', 22.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '18 days'),
(12, 'ORD-AURA-1012', 12, 1, 'Bakuchiol Retinol-Alternative Night Cream 50ml', 'CANCELLED', CURRENT_TIMESTAMP - INTERVAL '7 days', NULL, 62.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '7 days'),
(13, 'ORD-AURA-1013', 13, 1, 'AHA/BHA Exfoliating Glow Tonic 200ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '3 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 39.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(14, 'ORD-AURA-1014', 14, 1, 'Hydrating Glow Serum 50ml + Night Oil Duo', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '8 days', CURRENT_TIMESTAMP - INTERVAL '5 days', 96.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '8 days'),
(15, 'ORD-AURA-1015', 15, 1, 'Calming Centella Repair Balm 40ml', 'PROCESSING', CURRENT_TIMESTAMP - INTERVAL '6 hours', NULL, 30.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '6 hours'),
(16, 'ORD-AURA-1016', 16, 1, 'Botanical Cleansing Oil 150ml (Glass Dispenser)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 44.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(17, 'ORD-AURA-1017', 17, 1, 'Ultimate Glow Discovery Set (4 Travel Minis)', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '1 day', NULL, 55.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(18, 'ORD-AURA-1018', 18, 1, 'Radiant Vitamin C Glow Drops 30ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '25 days', CURRENT_TIMESTAMP - INTERVAL '21 days', 52.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '25 days'),
(19, 'ORD-AURA-1019', 19, 1, 'Nourishing Shea Hand Butter 75ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '5 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 24.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(20, 'ORD-AURA-1020', 20, 1, 'Hydrating Hyaluronic Essence 100ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 46.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(21, 'ORD-AURA-1021', 21, 1, 'Restorative Sleep Enzyme Mask 75g', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '2 days', NULL, 48.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(22, 'ORD-AURA-1022', 22, 1, 'Brightening Turmeric Facial Polish 100g', 'PROCESSING', CURRENT_TIMESTAMP - INTERVAL '12 hours', NULL, 35.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '12 hours'),
(23, 'ORD-AURA-1023', 23, 1, 'Hydrating Glow Serum 50ml (Glass Dropper Bottle)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '3 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 48.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(24, 'ORD-AURA-1024', 24, 1, 'Collagen Boost Firming Elixir 30ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '14 days', CURRENT_TIMESTAMP - INTERVAL '11 days', 72.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '14 days'),
(25, 'ORD-AURA-1025', 25, 1, 'Pure Rose Petal Facial Mist 100ml', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '6 days', CURRENT_TIMESTAMP - INTERVAL '3 days', 29.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '6 days'),
(26, 'ORD-AURA-1026', 26, 1, 'Sensitive Skin Probiotic Gel Moisturizer 50ml', 'RETURNED', CURRENT_TIMESTAMP - INTERVAL '16 days', CURRENT_TIMESTAMP - INTERVAL '13 days', 50.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '16 days'),
(27, 'ORD-AURA-1027', 27, 1, 'Aura Luxe Skincare Vanity Bag & Headband', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 32.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '4 days'),

-- Apex Electronics orders (Brand 2)
(28, 'ORD-APEX-2028', 28, 2, 'Apex Wave ANC Wireless Earbuds (Graphite)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '5 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 149.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(29, 'ORD-APEX-2029', 29, 2, 'Apex Studio Master 40mm Reference Headphones', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '2 days', NULL, 219.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(30, 'ORD-APEX-2030', 30, 2, 'Apex BassBoost Portable Bluetooth Speaker', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '9 days', CURRENT_TIMESTAMP - INTERVAL '6 days', 89.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '9 days'),
(31, 'ORD-APEX-2031', 31, 2, 'Apex Pro USB-C Condenser Microphone', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '15 days', CURRENT_TIMESTAMP - INTERVAL '12 days', 129.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '15 days'),
(32, 'ORD-APEX-2032', 32, 2, 'Apex Hi-Fi Desktop DAC & Headphone Amp', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '35 days', CURRENT_TIMESTAMP - INTERVAL '31 days', 199.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '35 days'),
(33, 'ORD-APEX-2033', 33, 2, 'Apex Wireless TV Soundbar with Subwoofer', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 349.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(34, 'ORD-APEX-2034', 34, 2, 'Apex SoundLink Multi-Device Bluetooth Adapter', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '1 day', NULL, 49.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(35, 'ORD-APEX-2035', 35, 2, 'Apex Gaming Headset with Boom Mic & 7.1 Surround', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '8 days', CURRENT_TIMESTAMP - INTERVAL '5 days', 119.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '8 days'),
(36, 'ORD-APEX-2036', 36, 2, 'Apex Elite Braided Balanced Audio Cable (2m)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '10 days', CURRENT_TIMESTAMP - INTERVAL '7 days', 39.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '10 days'),
(37, 'ORD-APEX-2037', 37, 2, 'Apex Pro ANC Studio Headphones (Silver)', 'PROCESSING', CURRENT_TIMESTAMP - INTERVAL '8 hours', NULL, 299.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '8 hours'),
(38, 'ORD-APEX-2038', 38, 2, 'Apex Wave Earbuds Replacement Charging Case', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '14 days', CURRENT_TIMESTAMP - INTERVAL '11 days', 59.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '14 days'),
(39, 'ORD-APEX-2039', 39, 2, 'Apex Sonic Boom Outdoor Waterproof Speaker', 'CANCELLED', CURRENT_TIMESTAMP - INTERVAL '5 days', NULL, 159.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(40, 'ORD-APEX-2040', 40, 2, 'Apex Studio Master 40mm Reference Headphones', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '22 days', CURRENT_TIMESTAMP - INTERVAL '19 days', 219.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '22 days'),
(41, 'ORD-APEX-2041', 41, 2, 'Apex Magnetic Wireless Charging Stand', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '3 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 45.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(42, 'ORD-APEX-2042', 42, 2, 'Apex Pro ANC Studio Headphones (Black)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '40 days', CURRENT_TIMESTAMP - INTERVAL '36 days', 299.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '40 days'),
(43, 'ORD-APEX-2043', 43, 2, 'Apex Broadcast Mic Arm & Shock Mount Kit', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '2 days', NULL, 69.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(44, 'ORD-APEX-2044', 44, 2, 'Apex Wave ANC Wireless Earbuds (White)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '7 days', CURRENT_TIMESTAMP - INTERVAL '4 days', 149.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '7 days'),
(45, 'ORD-APEX-2045', 45, 2, 'Apex High-Res Audio Player 64GB', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '12 days', CURRENT_TIMESTAMP - INTERVAL '9 days', 249.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '12 days'),
(46, 'ORD-APEX-2046', 46, 2, 'Apex Noise-Isolating Memory Foam Ear Cushions', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '6 days', CURRENT_TIMESTAMP - INTERVAL '3 days', 29.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '6 days'),
(47, 'ORD-APEX-2047', 47, 2, 'Apex BassBoost Mini Portable Speaker', 'PROCESSING', CURRENT_TIMESTAMP - INTERVAL '4 hours', NULL, 59.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '4 hours'),
(48, 'ORD-APEX-2048', 48, 2, 'Apex Dual-Channel Wireless Mic System', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '11 days', CURRENT_TIMESTAMP - INTERVAL '8 days', 189.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '11 days'),
(49, 'ORD-APEX-2049', 49, 2, 'Apex Optical Audio Toslink Cable (3m)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '1 day', 19.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(50, 'ORD-APEX-2050', 50, 2, 'Apex Pro Studio Monitor Pair (5-inch Active)', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '17 days', CURRENT_TIMESTAMP - INTERVAL '14 days', 399.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '17 days'),
(51, 'ORD-APEX-2051', 51, 2, 'Apex Wave Sport Waterproof Earhooks', 'SHIPPED', CURRENT_TIMESTAMP - INTERVAL '1 day', NULL, 35.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(52, 'ORD-APEX-2052', 52, 2, 'Apex Studio Master Reference Headphones', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '2 days', 219.00, 'USD', CURRENT_TIMESTAMP - INTERVAL '4 days')
ON CONFLICT (id) DO NOTHING;

-- 3. Insert 50 Seed Conversations (IDs 2 to 51)
INSERT INTO conversations (id, brand_id, customer_id, order_id, channel, status, created_at, updated_at) VALUES
-- Conversations for Aura Skincare (Brand 1)
(2, 1, 3, 3, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '2 hours', CURRENT_TIMESTAMP - INTERVAL '30 minutes'),
(3, 1, 4, 4, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '1 day', CURRENT_TIMESTAMP - INTERVAL '5 hours'),
(4, 1, 5, 5, 'WEB_CHAT', 'RESOLVED', CURRENT_TIMESTAMP - INTERVAL '3 days', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(5, 1, 6, 6, 'WHATSAPP', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '4 hours', CURRENT_TIMESTAMP - INTERVAL '1 hour'),
(6, 1, 7, 7, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 day', CURRENT_TIMESTAMP - INTERVAL '45 minutes'),
(7, 1, 8, 8, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '18 hours', CURRENT_TIMESTAMP - INTERVAL '3 hours'),
(8, 1, 9, 9, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '5 hours', CURRENT_TIMESTAMP - INTERVAL '20 minutes'),
(9, 1, 10, 10, 'SMS', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '6 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(10, 1, 11, 11, 'WEB_CHAT', 'RESOLVED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(11, 1, 12, 12, 'EMAIL', 'CLOSED', CURRENT_TIMESTAMP - INTERVAL '6 days', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(12, 1, 13, 13, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '3 hours', CURRENT_TIMESTAMP - INTERVAL '15 minutes'),
(13, 1, 14, 14, 'WHATSAPP', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '12 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(14, 1, 15, 15, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP - INTERVAL '10 minutes'),
(15, 1, 16, 16, 'EMAIL', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '8 hours', CURRENT_TIMESTAMP - INTERVAL '1 hour'),
(16, 1, 17, 17, 'SMS', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '14 hours', CURRENT_TIMESTAMP - INTERVAL '4 hours'),
(17, 1, 18, 18, 'WEB_CHAT', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '1 day', CURRENT_TIMESTAMP - INTERVAL '6 hours'),
(18, 1, 19, 19, 'WEB_CHAT', 'RESOLVED', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(19, 1, 20, 20, 'WHATSAPP', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '2 hours', CURRENT_TIMESTAMP - INTERVAL '25 minutes'),
(20, 1, 21, 21, 'EMAIL', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '5 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(21, 1, 22, 22, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '3 hours', CURRENT_TIMESTAMP - INTERVAL '40 minutes'),
(22, 1, 23, 23, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP - INTERVAL '5 minutes'),
(23, 1, 24, 24, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '10 hours', CURRENT_TIMESTAMP - INTERVAL '3 hours'),
(24, 1, 25, 25, 'SMS', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '7 hours', CURRENT_TIMESTAMP - INTERVAL '1 hour'),
(25, 1, 26, 26, 'WEB_CHAT', 'CLOSED', CURRENT_TIMESTAMP - INTERVAL '5 days', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(26, 1, 27, 27, 'WEB_CHAT', 'RESOLVED', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conversations for Apex Electronics (Brand 2)
(27, 2, 28, 28, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '3 hours', CURRENT_TIMESTAMP - INTERVAL '20 minutes'),
(28, 2, 29, 29, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '1 day', CURRENT_TIMESTAMP - INTERVAL '4 hours'),
(29, 2, 30, 30, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '6 hours', CURRENT_TIMESTAMP - INTERVAL '1 hour'),
(30, 2, 31, 31, 'WHATSAPP', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '15 hours', CURRENT_TIMESTAMP - INTERVAL '5 hours'),
(31, 2, 32, 32, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '8 hours'),
(32, 2, 33, 33, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '2 hours', CURRENT_TIMESTAMP - INTERVAL '10 minutes'),
(33, 2, 34, 34, 'SMS', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '5 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(34, 2, 35, 35, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '4 hours', CURRENT_TIMESTAMP - INTERVAL '50 minutes'),
(35, 2, 36, 36, 'EMAIL', 'RESOLVED', CURRENT_TIMESTAMP - INTERVAL '3 days', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(36, 2, 37, 37, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP - INTERVAL '15 minutes'),
(37, 2, 38, 38, 'WHATSAPP', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '12 hours', CURRENT_TIMESTAMP - INTERVAL '3 hours'),
(38, 2, 39, 39, 'WEB_CHAT', 'CLOSED', CURRENT_TIMESTAMP - INTERVAL '4 days', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(39, 2, 40, 40, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '18 hours', CURRENT_TIMESTAMP - INTERVAL '6 hours'),
(40, 2, 41, 41, 'SMS', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '4 hours', CURRENT_TIMESTAMP - INTERVAL '1 hour'),
(41, 2, 42, 42, 'WEB_CHAT', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '1 day', CURRENT_TIMESTAMP - INTERVAL '7 hours'),
(42, 2, 43, 43, 'EMAIL', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '8 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(43, 2, 44, 44, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '2 hours', CURRENT_TIMESTAMP - INTERVAL '30 minutes'),
(44, 2, 45, 45, 'WHATSAPP', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '10 hours', CURRENT_TIMESTAMP - INTERVAL '4 hours'),
(45, 2, 46, 46, 'WEB_CHAT', 'RESOLVED', CURRENT_TIMESTAMP - INTERVAL '2 days', CURRENT_TIMESTAMP - INTERVAL '1 day'),
(46, 2, 47, 47, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP - INTERVAL '10 minutes'),
(47, 2, 48, 48, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '14 hours', CURRENT_TIMESTAMP - INTERVAL '5 hours'),
(48, 2, 49, 49, 'SMS', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '6 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(49, 2, 50, 50, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '3 hours', CURRENT_TIMESTAMP - INTERVAL '45 minutes'),
(50, 2, 51, 51, 'EMAIL', 'WAITING_AGENT', CURRENT_TIMESTAMP - INTERVAL '9 hours', CURRENT_TIMESTAMP - INTERVAL '3 hours'),
(51, 2, 52, 52, 'WEB_CHAT', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP - INTERVAL '5 minutes')
ON CONFLICT (id) DO NOTHING;

-- -- 4. Insert Messages for these 50 Conversations
INSERT INTO messages (conversation_id, sender_type, sender_name, content, created_at) VALUES
-- Conv 2: Damaged bottle report (Aura)
(2, 'CUSTOMER', 'Michael Brown', 'Hello, my Vitamin C Glow Drops arrived today but the dropper neck was completely shattered inside the package.', CURRENT_TIMESTAMP - INTERVAL '2 hours'),
(2, 'AGENT', 'Aura Support Specialist', 'Hi Michael, I am so sorry to hear that your bottle arrived broken! Under our damaged item policy, you are fully covered. Could you please reply with a quick photo of the broken dropper?', CURRENT_TIMESTAMP - INTERVAL '1 hour 45 minutes'),
(2, 'CUSTOMER', 'Michael Brown', 'Here is the photo of the broken glass. Looking forward to the replacement!', CURRENT_TIMESTAMP - INTERVAL '30 minutes'),

-- Conv 3: Shipping inquiry (Aura)
(3, 'CUSTOMER', 'Emily Davis', 'Can you provide tracking details for my order ORD-AURA-1004? When will it arrive?', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 4: Resolved return request within policy (Aura)
(4, 'CUSTOMER', 'James Wilson', 'I ordered the rosewater toner but realized I ordered the wrong scent. Can I return it?', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(4, 'AGENT', 'Aura Support Specialist', 'Hi James, as long as the seal is unopened, you can return it within 7 days of delivery. We have generated your return authorization!', CURRENT_TIMESTAMP - INTERVAL '2 days'),

-- Conv 5: Order cancellation within 1-hour window (Aura)
(5, 'CUSTOMER', 'Jessica Taylor', 'I just placed order ORD-AURA-1006 20 minutes ago. Can I cancel it before it ships?', CURRENT_TIMESTAMP - INTERVAL '4 hours'),

-- Conv 6: Expired return window query (Aura - temporal guardrail scenario)
(6, 'CUSTOMER', 'Daniel Martinez', 'I received my Ceramide Moisturizer 9 days ago. My skin feels dry, can I get a refund?', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 7: Expired refund window 20 days ago (Aura)
(7, 'CUSTOMER', 'Sophia Anderson', 'I received this eye cream 20 days ago. Can I get a refund?', CURRENT_TIMESTAMP - INTERVAL '18 hours'),

-- Conv 8: Leaking bottle report (Aura)
(8, 'CUSTOMER', 'Matthew Thomas', 'My clay mask arrived with the seal burst and cream leaking all over the shipping box.', CURRENT_TIMESTAMP - INTERVAL '5 hours'),

-- Conv 9: Address change request (Aura)
(9, 'CUSTOMER', 'Olivia Jackson', 'Can I change my delivery address for ORD-AURA-1010? It is currently marked as shipped.', CURRENT_TIMESTAMP - INTERVAL '6 hours'),

-- Conv 10: Product ingredient question (Aura)
(10, 'CUSTOMER', 'Ethan White', 'Is the lip therapy mask safe for sensitive and eczema-prone lips?', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(10, 'AGENT', 'Aura Support Specialist', 'Hi Ethan, yes! Our lip therapy mask is 100% hypoallergenic, organic, and dermatologist tested for sensitive lips.', CURRENT_TIMESTAMP - INTERVAL '3 days'),

-- Conv 11: Cancellation confirmation (Aura)
(11, 'CUSTOMER', 'Ava Harris', 'Please confirm that order ORD-AURA-1012 has been cancelled and refunded.', CURRENT_TIMESTAMP - INTERVAL '6 days'),
(11, 'AGENT', 'Aura Support Specialist', 'Hi Ava, your order was successfully cancelled and the full refund of $62.00 has been credited to your original payment method.', CURRENT_TIMESTAMP - INTERVAL '5 days'),

-- Conv 12: Broken glass bottle inquiry (Aura)
(12, 'CUSTOMER', 'Alexander Martin', 'My Exfoliating Glow Tonic arrived broken. Glass shards were everywhere.', CURRENT_TIMESTAMP - INTERVAL '3 hours'),

-- Conv 13: Discount code inquiry (Aura)
(13, 'CUSTOMER', 'Isabella Thompson', 'I forgot to apply promo code GLOW20 to my duo set order. Can you apply it retroactively?', CURRENT_TIMESTAMP - INTERVAL '12 hours'),

-- Conv 14: Order status inquiry (Aura)
(14, 'CUSTOMER', 'William Garcia', 'When will my Centella Repair Balm ship? Placed it earlier today.', CURRENT_TIMESTAMP - INTERVAL '1 hour'),

-- Conv 15: Missing item from package (Aura)
(15, 'CUSTOMER', 'Mia Martinez', 'I received my package today but only 1 of the 2 cleansing oils was in the box.', CURRENT_TIMESTAMP - INTERVAL '8 hours'),

-- Conv 16: International shipping timeline (Aura)
(16, 'CUSTOMER', 'Benjamin Robinson', 'Do you ship to Canada, and how many days does express delivery take?', CURRENT_TIMESTAMP - INTERVAL '14 hours'),

-- Conv 17: Requesting refund after 21 days (Aura)
(17, 'CUSTOMER', 'Charlotte Clark', 'I received this product 21 days ago. It causes me redness, please refund my payment.', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 18: Product praise (Aura)
(18, 'CUSTOMER', 'Lucas Rodriguez', 'Just wanted to say the hand butter is fantastic! Thank you for the quick delivery.', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(18, 'AGENT', 'Aura Support Specialist', 'Thank you so much Lucas! We are thrilled to hear you love our botanical formula.', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 19: Allergic reaction inquiry (Aura)
(19, 'CUSTOMER', 'Amelia Lewis', 'My skin had a reaction to the hyaluronic essence. What is your policy for adverse reactions?', CURRENT_TIMESTAMP - INTERVAL '2 hours'),

-- Conv 20: Tracking number not updating (Aura)
(20, 'CUSTOMER', 'Henry Lee', 'The tracking number for my order shows label created for 48 hours. Has it been picked up?', CURRENT_TIMESTAMP - INTERVAL '5 hours'),

-- Conv 21: Change item in processing order (Aura)
(21, 'CUSTOMER', 'Harper Walker', 'Can I swap the facial polish for the oat cleanser before my order ships out?', CURRENT_TIMESTAMP - INTERVAL '3 hours'),

-- Conv 22: Photo of broken glass dropper attached (Aura)
(22, 'CUSTOMER', 'Sebastian Hall', 'Here is the photo of the broken bottle and damaged package as requested. I would prefer a replacement.', CURRENT_TIMESTAMP - INTERVAL '1 hour'),

-- Conv 23: Return policy inquiry outside 7 days (Aura)
(23, 'CUSTOMER', 'Evelyn Allen', 'I bought this collagen elixir 11 days ago as a gift. Can I still return it for store credit?', CURRENT_TIMESTAMP - INTERVAL '10 hours'),

-- Conv 24: Express shipping upgrade (Aura)
(24, 'CUSTOMER', 'Jack Young', 'Is it possible to upgrade my order to overnight delivery?', CURRENT_TIMESTAMP - INTERVAL '7 hours'),

-- Conv 25: Processed return confirmation (Aura)
(25, 'CUSTOMER', 'Abigail Hernandez', 'Has my returned probiotic moisturizer arrived at your warehouse?', CURRENT_TIMESTAMP - INTERVAL '5 days'),
(25, 'AGENT', 'Aura Support Specialist', 'Hi Abigail, yes! We received your returned item and your refund has been processed in full.', CURRENT_TIMESTAMP - INTERVAL '4 days'),

-- Conv 26: Gift receipt request (Aura)
(26, 'CUSTOMER', 'Owen King', 'Could you email me an invoice with prices hidden for a gift?', CURRENT_TIMESTAMP - INTERVAL '2 days'),

-- Conv 27: Apex Audio defective right earbud (Apex)
(27, 'CUSTOMER', 'Emily Wright', 'The right earbud on my Apex Wave ANC has no sound and does not charge in the case.', CURRENT_TIMESTAMP - INTERVAL '3 hours'),

-- Conv 28: Apex delivery signature inquiry (Apex)
(28, 'CUSTOMER', 'Samuel Lopez', 'I will not be home for the courier delivery. Does Apex require a physical signature?', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 29: Apex Bluetooth pairing issue (Apex)
(29, 'CUSTOMER', 'Elizabeth Hill', 'My portable speaker keeps disconnecting from my iPhone after 2 minutes of playback.', CURRENT_TIMESTAMP - INTERVAL '6 hours'),

-- Conv 30: Apex microphone gain defect (Apex)
(30, 'CUSTOMER', 'Leo Scott', 'The USB microphone has heavy background hiss even on minimum gain. Can I get a replacement?', CURRENT_TIMESTAMP - INTERVAL '15 hours'),

-- Conv 31: Apex return request after 31 days (Apex - temporal violation)
(31, 'CUSTOMER', 'Ella Green', 'I received this desktop DAC 31 days ago. I want to return it for a refund.', CURRENT_TIMESTAMP - INTERVAL '2 days'),

-- Conv 32: Apex TV Soundbar optical input issue (Apex)
(32, 'CUSTOMER', 'Julian Adams', 'The optical audio port on the back of my soundbar is loose and cuts out audio.', CURRENT_TIMESTAMP - INTERVAL '2 hours'),

-- Conv 33: Apex shipping transit question (Apex)
(33, 'CUSTOMER', 'Avery Baker', 'How many days will secure courier freight take to reach Chicago for ORD-APEX-2034?', CURRENT_TIMESTAMP - INTERVAL '5 hours'),

-- Conv 34: Apex Gaming headset DOA (Apex)
(34, 'CUSTOMER', 'Gabriel Gonzalez', 'Headset arrived dead on arrival. Boom mic is cracked. Do I need to pay for return shipping?', CURRENT_TIMESTAMP - INTERVAL '4 hours'),

-- Conv 35: Apex audio cable replacement (Apex)
(35, 'CUSTOMER', 'Scarlett Nelson', 'Thank you for sending the replacement balanced cable, it solved the static issue completely!', CURRENT_TIMESTAMP - INTERVAL '3 days'),
(35, 'AGENT', 'Apex Technical Support', 'You are very welcome Scarlett! Happy to hear your audio setup is performing optimally.', CURRENT_TIMESTAMP - INTERVAL '2 days'),

-- Conv 36: Apex order cancellation before carrier scan (Apex)
(36, 'CUSTOMER', 'Carter Carter', 'I accidentally selected the silver headphones instead of black. Please cancel my order before dispatch.', CURRENT_TIMESTAMP - INTERVAL '1 hour'),

-- Conv 37: Apex earbud case lost (Apex)
(37, 'CUSTOMER', 'Grace Mitchell', 'I lost my wireless charging case while traveling. Does warranty cover accidental loss?', CURRENT_TIMESTAMP - INTERVAL '12 hours'),

-- Conv 38: Apex order cancelled successfully (Apex)
(38, 'CUSTOMER', 'Jayden Perez', 'Please verify my outdoor speaker cancellation.', CURRENT_TIMESTAMP - INTERVAL '4 days'),
(38, 'AGENT', 'Apex Technical Support', 'Order ORD-APEX-2039 was cancelled prior to barcode scan. Full refund of $159.00 issued.', CURRENT_TIMESTAMP - INTERVAL '3 days'),

-- Conv 39: Apex headphone headband rattle (Apex)
(39, 'CUSTOMER', 'Chloe Roberts', 'There is an audible plastic rattle inside the left earcup when moving my head.', CURRENT_TIMESTAMP - INTERVAL '18 hours'),

-- Conv 40: Apex wireless charger compatibility (Apex)
(40, 'CUSTOMER', 'Luke Turner', 'Does the Apex magnetic charging stand support Android Qi2 phones?', CURRENT_TIMESTAMP - INTERVAL '4 hours'),

-- Conv 41: Apex return request after 36 days (Apex - policy violation)
(41, 'CUSTOMER', 'Victoria Phillips', 'I purchased the ANC Studio Headphones 36 days ago and want to return them for a full refund.', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 42: Apex broadcast mic arm mounting clamp (Apex)
(42, 'CUSTOMER', 'Anthony Campbell', 'What is the maximum desk thickness supported by the C-clamp on this mic arm?', CURRENT_TIMESTAMP - INTERVAL '8 hours'),

-- Conv 43: Apex ANC firmware update question (Apex)
(43, 'CUSTOMER', 'Riley Parker', 'How do I update the noise cancellation firmware on my earbuds?', CURRENT_TIMESTAMP - INTERVAL '2 hours'),

-- Conv 44: Apex high-res player battery drain (Apex)
(44, 'CUSTOMER', 'Isaac Evans', 'The battery on my DAP drains from 100% to zero in under 3 hours of FLAC playback.', CURRENT_TIMESTAMP - INTERVAL '10 hours'),

-- Conv 45: Apex ear cushion cleaning advice (Apex)
(45, 'CUSTOMER', 'Aria Edwards', 'How should I clean the memory foam ear cushions without damaging the protein leather?', CURRENT_TIMESTAMP - INTERVAL '2 days'),
(45, 'AGENT', 'Apex Technical Support', 'Hi Aria, use a lightly damp microfiber cloth with mild soapy water. Never soak the foam pads in water.', CURRENT_TIMESTAMP - INTERVAL '1 day'),

-- Conv 46: Apex order address change (Apex)
(46, 'CUSTOMER', 'Dylan Collins', 'Can I update the delivery suite number on my processing order ORD-APEX-2047?', CURRENT_TIMESTAMP - INTERVAL '1 hour'),

-- Conv 47: Apex wireless microphone range (Apex)
(47, 'CUSTOMER', 'Zoey Stewart', 'The wireless transmitter drops signal at 15 meters line of sight, but specs say 50 meters.', CURRENT_TIMESTAMP - INTERVAL '14 hours'),

-- Conv 48: Apex Toslink cable inquiry (Apex)
(48, 'CUSTOMER', 'Nathan Sanchez', 'Does this optical cable support Dolby Digital 5.1 surround pass-through?', CURRENT_TIMESTAMP - INTERVAL '6 hours'),

-- Conv 49: Apex Studio monitors humming noise (Apex)
(49, 'CUSTOMER', 'Penelope Morris', 'There is a low 60Hz ground loop hum when connecting monitors via unbalanced RCA.', CURRENT_TIMESTAMP - INTERVAL '3 hours'),

-- Conv 50: Apex courier delivery update (Apex)
(50, 'CUSTOMER', 'Caleb Rogers', 'Can you provide the courier tracking link for ORD-APEX-2051?', CURRENT_TIMESTAMP - INTERVAL '9 hours'),

-- Conv 51: Apex headphone sound quality praise (Apex)
(51, 'CUSTOMER', 'Layla Reed', 'The soundstage on these reference headphones is incredible! Very impressed with the build quality.', CURRENT_TIMESTAMP - INTERVAL '1 hour');

-- 5. Synchronize all database sequences to prevent ID conflict errors on subsequent inserts
SELECT setval('customers_id_seq', (SELECT COALESCE(MAX(id), 1) FROM customers));
SELECT setval('orders_id_seq', (SELECT COALESCE(MAX(id), 1) FROM orders));
SELECT setval('conversations_id_seq', (SELECT COALESCE(MAX(id), 1) FROM conversations));
SELECT setval('messages_id_seq', (SELECT COALESCE(MAX(id), 1) FROM messages));
