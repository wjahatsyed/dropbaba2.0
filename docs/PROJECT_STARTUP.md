# Dropbaba 2.0 — Project Startup Guide

## Goal
Launch a multi-vertical mobility and delivery platform (ride-hailing + food + groceries) with a modern web, iOS, and Android experience, supported by a scalable backend and strong product/project management.

## 1) Product & Program Foundations
### Core product pillars
- **Mobility**: Rider app + Driver app + Dispatch/ops tools.
- **Food delivery**: Customer app + Courier app + Merchant portal + Ops tools.
- **Grocery delivery**: Customer app + Picker/courier flows + Store partner tools.

### Initial user journeys (MVP)
1. **Customer**: Search → order → pay → track → rate.
2. **Driver/Courier**: Onboard → accept job → pickup → deliver → earnings.
3. **Merchant/Store**: Onboard → manage menu/inventory → fulfill orders.
4. **Ops/Admin**: Monitor live jobs, refunds, promos, and support tickets.

### Key outcomes for MVP
- 30–45 min delivery SLA for food/grocery within target zones.
- 95%+ on-time ride/driver accept rates.
- Payment success > 98%.
- Merchant onboarding < 24 hours.

## 2) Project Management Setup (Jira)
### Create a Jira Cloud project
1. **Project type**: `Software` → `Scrum` (or `Kanban` if flow-based).
2. **Project key**: `DRP` (Dropbaba Platform).
3. **Issue types**: Epic, Story, Task, Bug, Spike.
4. **Components**: Mobility, Food, Grocery, Payments, Identity, Ops, Data.

### Suggested workflow
- **Backlog** → **Ready** → **In Progress** → **In Review** → **QA** → **Done**.
- Add **Blocked** and **Needs Info** as statuses or flags.

### Boards
- **Product board**: roadmapping + epics + discovery.
- **Delivery board**: team execution and sprint tracking.

### Jira + Git integration
1. Link Git provider (GitHub/GitLab/Bitbucket) in Jira settings.
2. **Branch naming**: `DRP-123/<short-title>` (maps to Jira issue keys).
3. **Commit messages**: `DRP-123: short summary`.
4. Enable automation rules to move issues based on PR status.

### Required Jira fields
- **Definition of Ready**: scope, acceptance criteria, dependencies.
- **Definition of Done**: tests, code review, QA, docs.

## 3) Alternative Tools (if not Jira)
- **Linear**: faster UX, strong GitHub integration.
- **Shortcut (Clubhouse)**: product-friendly, easy workflows.
- **Azure DevOps**: enterprise planning + repo + pipelines.

## 4) Team Structure (suggested)
- **Product**: PM, Designer, UX Researcher.
- **Engineering**:
  - Backend: 2–4 engineers
  - Mobile: iOS + Android (or Flutter/React Native team)
  - Web: Frontend engineer(s)
  - QA: 1–2 engineers
  - DevOps/SRE: 1 engineer
- **Operations**: Support + merchant onboarding + logistics.

## 5) Development Streams & Backlog Seeds
### Epics (first 90 days)
1. **Identity & Authentication**
2. **Customer Experience (Order + Tracking)**
3. **Courier/Driver Experience**
4. **Merchant/Store Portal**
5. **Payments & Wallet**
6. **Pricing/Promotions**
7. **Dispatch & ETA**
8. **Ops Console + Support**

### Example Stories
- As a customer, I can sign up with phone OTP.
- As a courier, I can see available orders and accept one.
- As a merchant, I can update menu items and stock.
- As ops, I can issue refunds and view trip/order history.

## 6) Architecture Starting Point
### Backend
- **Services**: auth, catalog/menu, ordering, dispatch, payments, notifications.
- **Data**: PostgreSQL + Redis (cache) + S3-compatible storage.
- **Async**: Kafka/RabbitMQ for event-driven workflows.

### Mobile
- **Customer app**: React Native or native iOS/Android.
- **Courier app**: React Native or native iOS/Android.

### Web
- **Customer web**: Next.js/React.
- **Merchant portal**: React + component library.
- **Ops console**: Admin UI with role-based access.

## 7) Delivery Cadence
- **Sprints**: 2 weeks.
- **Release**: monthly.
- **Roadmap**: quarterly.

## 8) Documentation Checklist
- Product Requirements Document (PRD)
- System Design (HLD/LLD)
- API contracts (OpenAPI)
- Runbooks & on-call procedures
- Data privacy and security policies

---

## Quick Start Checklist
- [ ] Create Jira project + boards
- [ ] Define epics and MVP scope
- [ ] Set repo structure and branching rules
- [ ] Establish CI/CD + environments (dev/staging/prod)
- [ ] Draft PRD + system design docs
- [ ] Define MVP release plan
