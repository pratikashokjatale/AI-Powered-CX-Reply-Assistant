-- Flyway Migration V1: Initial Schema for AI-Powered CX Reply Assistant

CREATE TABLE IF NOT EXISTS brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    tone_guidelines TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS knowledge_articles (
    id BIGSERIAL PRIMARY KEY,
    brand_id BIGINT NOT NULL REFERENCES brands(id) ON DELETE CASCADE,
    category VARCHAR(50) NOT NULL, -- RETURN, REFUND, SHIPPING, CANCELLATION, GENERAL
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    keywords VARCHAR(500),
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(50),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    order_number VARCHAR(100) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL REFERENCES customers(id),
    brand_id BIGINT NOT NULL REFERENCES brands(id),
    item_summary VARCHAR(255) NOT NULL,
    order_status VARCHAR(50) NOT NULL, -- DELIVERED, SHIPPED, PROCESSING, CANCELLED, RETURNED
    order_date TIMESTAMP WITH TIME ZONE NOT NULL,
    delivery_date TIMESTAMP WITH TIME ZONE,
    total_amount NUMERIC(10, 2) NOT NULL,
    currency VARCHAR(10) DEFAULT 'USD' NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS conversations (
    id BIGSERIAL PRIMARY KEY,
    brand_id BIGINT NOT NULL REFERENCES brands(id),
    customer_id BIGINT NOT NULL REFERENCES customers(id),
    order_id BIGINT REFERENCES orders(id),
    channel VARCHAR(50) DEFAULT 'WEB_CHAT' NOT NULL,
    status VARCHAR(50) DEFAULT 'OPEN' NOT NULL, -- OPEN, WAITING_AGENT, RESOLVED, CLOSED
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    id BIGSERIAL PRIMARY KEY,
    conversation_id BIGINT NOT NULL REFERENCES conversations(id) ON DELETE CASCADE,
    sender_type VARCHAR(50) NOT NULL, -- CUSTOMER, AGENT, SYSTEM
    sender_name VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS ai_interaction_logs (
    id BIGSERIAL PRIMARY KEY,
    conversation_id BIGINT REFERENCES conversations(id),
    brand_id BIGINT REFERENCES brands(id),
    customer_message TEXT NOT NULL,
    retrieved_context TEXT,
    ai_generated_response TEXT,
    agent_edited_response TEXT,
    final_response TEXT,
    guardrail_status VARCHAR(50), -- PASSED, WARNING, BLOCKED, MISSING_CONTEXT
    guardrail_notes TEXT,
    model_used VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Indexes for optimal retrieval performance
CREATE INDEX IF NOT EXISTS idx_knowledge_brand ON knowledge_articles(brand_id);
CREATE INDEX IF NOT EXISTS idx_knowledge_category ON knowledge_articles(category);
CREATE INDEX IF NOT EXISTS idx_conversations_brand ON conversations(brand_id);
CREATE INDEX IF NOT EXISTS idx_conversations_customer ON conversations(customer_id);
CREATE INDEX IF NOT EXISTS idx_messages_conversation ON messages(conversation_id);
CREATE INDEX IF NOT EXISTS idx_orders_customer ON orders(customer_id);
CREATE INDEX IF NOT EXISTS idx_ai_logs_conversation ON ai_interaction_logs(conversation_id);
