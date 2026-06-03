
console.log("Welcome to the Community Portal");

window.addEventListener('load', () => {
    alert("Page loaded successfully! Ready to explore community events.");
});

const eventName = "Summer Music Festival";
const eventDate = "June 15, 2024";
let availableSeats = 150;

const eventInfo = `Event: ${eventName}, Date: ${eventDate}, Seats: ${availableSeats}`;
console.log(eventInfo);

class Event {
    constructor(id, name, date, category, location, maxSeats, description, time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.location = location;
        this.maxSeats = maxSeats;
        this.availableSeats = maxSeats;
        this.description = description;
        this.time = time;
        this.registrations = [];
    }

    checkAvailability() {
        return this.availableSeats > 0;
    }

    registerUser(userName, email) {
        if (this.availableSeats > 0) {
            this.availableSeats--;
            this.registrations.push({ userName, email, registeredAt: new Date() });
            return true;
        }
        return false;
    }

    cancelRegistration(email) {
        const index = this.registrations.findIndex(reg => reg.email === email);
        if (index !== -1) {
            this.registrations.splice(index, 1);
            this.availableSeats++;
            return true;
        }
        return false;
    }
}

function getEventInfo(event) {
    console.log("Event Information:");
    Object.entries(event).forEach(([key, value]) => {
        if (key !== 'registrations') {
            console.log(`${key}: ${value}`);
        }
    });
}

function createCategoryTracker() {
    const categoryStats = {};

    return {
        addRegistration(category) {
            categoryStats[category] = (categoryStats[category] || 0) + 1;
        },
        getStats() {
            return { ...categoryStats };
        }
    };
}

const registrationTracker = createCategoryTracker();

function filterEventsByCategory(events, category, callback) {
    const filtered = events.filter(event => event.category.toLowerCase() === category.toLowerCase());
    if (callback && typeof callback === 'function') {
        return filtered.map(callback);
    }
    return filtered;
}

function createEventSearcher(allEvents) {
    return function(searchTerm, filterType = 'name') {
        return allEvents.filter(event => {
            const value = event[filterType].toString().toLowerCase();
            return value.includes(searchTerm.toLowerCase());
        });
    };
}

const events = [
    new Event(1, "Summer Music Festival", "2024-06-15", "Music", "Central Park", 100, "Live music performances from local artists", "10:00 AM"),
    new Event(2, "Community Baking Workshop", "2024-06-20", "Workshop", "Community Center", 30, "Learn bread making techniques", "2:00 PM"),
    new Event(3, "Youth Soccer Tournament", "2024-06-22", "Sports", "City Stadium", 50, "Friendly soccer match for all ages", "4:00 PM"),
    new Event(4, "Jazz Night", "2024-06-25", "Music", "Downtown Hall", 80, "Evening jazz performances", "7:00 PM"),
    new Event(5, "Children's Art Class", "2024-06-28", "Workshop", "Art Gallery", 25, "Creative painting and drawing for kids", "3:00 PM"),
    new Event(6, "Community Cleanup Day", "2024-07-01", "Volunteer", "Multiple Locations", 200, "Help keep our community clean", "8:00 AM"),
    new Event(7, "Dance Workshop", "2024-07-05", "Workshop", "Dance Studio", 40, "Learn contemporary dance moves", "6:00 PM"),
    new Event(8, "Technology Basics for Seniors", "2024-07-10", "Workshop", "Library", 35, "Introduction to computers and internet", "10:00 AM"),
];

function addEvent(id, name, date, category, location, maxSeats, description, time) {
    const newEvent = new Event(id, name, date, category, location, maxSeats, description, time);
    events.push(newEvent);
    console.log(`Event "${name}" added successfully!`);
    return newEvent;
}

function getMusicEvents() {
    return events.filter(event => event.category === "Music");
}

function formatEventCards() {
    return events.map(event => {
        return {
            title: `${event.name}`,
            display: `${event.category} on ${event.date} at ${event.location}`,
            available: event.availableSeats
        };
    });
}

function displayValidEvents(eventList) {
    console.log("Valid Events (Upcoming with Available Seats):");
    eventList.forEach((event, index) => {
        try {
            if (!event.name || !event.date) {
                throw new Error(`Invalid event data at index ${index}`);
            }

            const today = new Date();
            const eventDate = new Date(event.date);

            if (eventDate > today && event.checkAvailability()) {
                console.log(`✓ ${event.name} - ${event.date} at ${event.location} (${event.availableSeats} seats)`);
            } else if (!event.checkAvailability()) {
                console.log(`✗ ${event.name} - SOLD OUT`);
            }
        } catch (error) {
            console.error(`Error processing event: ${error.message}`);
        }
    });
}

function registerUserForEvent(eventId, userName, email) {
    try {
        if (!userName || !email) {
            throw new Error("Name and email are required");
        }

        if (!email.includes('@')) {
            throw new Error("Invalid email format");
        }

        const event = events.find(e => e.id === eventId);
        if (!event) {
            throw new Error("Event not found");
        }

        if (event.registerUser(userName, email)) {
            registrationTracker.addRegistration(event.category);
            console.log(`✓ ${userName} registered for ${event.name}`);
            return { success: true, message: "Registration successful!" };
        } else {
            throw new Error("No seats available");
        }
    } catch (error) {
        console.error(`Registration Error: ${error.message}`);
        return { success: false, message: error.message };
    }
}

function createEventCard(event) {
    const card = document.createElement('div');
    card.className = 'event-card';
    card.id = `event-${event.id}`;
    
    const statusClass = event.availableSeats > 0 ? 'available' : 'sold-out';
    const statusText = event.availableSeats > 0 ? `${event.availableSeats} seats` : 'SOLD OUT';

    card.innerHTML = `
        <div class="event-header">
            <h3>${event.name}</h3>
            <span class="event-category">${event.category}</span>
        </div>
        <div class="event-body">
            <p><strong>Date:</strong> ${event.date}</p>
            <p><strong>Time:</strong> ${event.time}</p>
            <p><strong>Location:</strong> ${event.location}</p>
            <p><strong>Description:</strong> ${event.description}</p>
            <p class="event-status ${statusClass}"><strong>Status:</strong> ${statusText}</p>
        </div>
        <div class="event-footer">
            <button class="register-btn" data-event-id="${event.id}" ${event.availableSeats === 0 ? 'disabled' : ''}>Register Now</button>
            <button class="details-btn" data-event-id="${event.id}">View Details</button>
        </div>
    `;

    return card;
}

function renderEvents(eventList) {
    const container = document.getElementById('eventsContainer');
    if (!container) return;

    container.innerHTML = '';
    
    if (eventList.length === 0) {
        container.innerHTML = '<p class="no-events">No events found.</p>';
        return;
    }

    eventList.forEach(event => {
        const card = createEventCard(event);
        container.appendChild(card);
    });
}

function updateEventUI(eventId) {
    const card = document.getElementById(`event-${eventId}`);
    if (card) {
        const event = events.find(e => e.id === eventId);
        const statusElement = card.querySelector('.event-status');
        const registerBtn = card.querySelector('.register-btn');
        
        if (event.availableSeats > 0) {
            statusElement.textContent = `Status: ${event.availableSeats} seats`;
            statusElement.className = 'event-status available';
            registerBtn.disabled = false;
        } else {
            statusElement.textContent = 'Status: SOLD OUT';
            statusElement.className = 'event-status sold-out';
            registerBtn.disabled = true;
        }
    }
}

function initializeEventHandlers() {
    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('register-btn')) {
            const eventId = parseInt(e.target.dataset.eventId);
            openRegistrationModal(eventId);
        }

        if (e.target.classList.contains('details-btn')) {
            const eventId = parseInt(e.target.dataset.eventId);
            showEventDetails(eventId);
        }

        if (e.target.classList.contains('filter-btn')) {
            const category = e.target.dataset.category;
            filterAndDisplayEvents(category);
        }

        if (e.target.id === 'submitRegistration') {
            submitRegistration();
        }

        if (e.target.id === 'closeModal') {
            closeRegistrationModal();
        }

        if (e.target.id === 'closeDetailsModal') {
            closeDetailsModal();
        }
    });

    const categoryFilter = document.getElementById('categoryFilter');
    if (categoryFilter) {
        categoryFilter.addEventListener('change', function(e) {
            filterAndDisplayEvents(e.target.value);
        });
    }

    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keydown', function(e) {
            if (e.key === 'Enter') {
                const searchTerm = e.target.value;
                const searcher = createEventSearcher(events);
                const results = searcher(searchTerm, 'name');
                renderEvents(results);
            }
        });
    }
}

function mockFetchEvents() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve({
                status: 'success',
                data: events
            });
        }, 1000);
    });
}

function mockPostRegistration(eventId, userData) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (userData.name && userData.email) {
                resolve({
                    status: 'success',
                    message: 'Registration received',
                    registrationId: Math.random().toString(36).substr(2, 9)
                });
            } else {
                reject(new Error('Missing required fields'));
            }
        }, 800);
    });
}

function loadEventsWithPromises() {
    showLoadingSpinner(true);
    
    mockFetchEvents()
        .then(response => {
            console.log("Events loaded:", response.data.length);
            showLoadingSpinner(false);
            renderEvents(response.data);
        })
        .catch(error => {
            console.error("Error loading events:", error);
            showLoadingSpinner(false);
            showErrorMessage("Failed to load events");
        });
}

async function loadEventsWithAsyncAwait() {
    try {
        showLoadingSpinner(true);
        const response = await mockFetchEvents();
        console.log("Events loaded asynchronously:", response.data.length);
        showLoadingSpinner(false);
        renderEvents(response.data);
    } catch (error) {
        console.error("Error loading events:", error);
        showLoadingSpinner(false);
        showErrorMessage("Failed to load events");
    }
}

async function registerUserAsyncAwait(eventId, userData) {
    try {
        showLoadingSpinner(true);
        const response = await mockPostRegistration(eventId, userData);
        showLoadingSpinner(false);
        console.log("Registration successful:", response.registrationId);
        return { success: true, ...response };
    } catch (error) {
        showLoadingSpinner(false);
        console.error("Registration failed:", error);
        return { success: false, message: error.message };
    }
}

function createRegistrationEmail(name, eventName = "Community Event", date = new Date().toISOString()) {
    return `Hello ${name}, you are registered for ${eventName} on ${date}`;
}

function displayEventSummary(event) {
    const { name, date, location, category, availableSeats } = event;
    console.log(`Event Summary:
        Name: ${name}
        Date: ${date}
        Location: ${location}
        Category: ${category}
        Available: ${availableSeats}`);
}

function getUpcomingEventsCopy() {
    const eventsCopy = [...events];
    return eventsCopy.filter(event => {
        const eventDate = new Date(event.date);
        return eventDate > new Date();
    });
}

let currentRegistrationEventId = null;

function openRegistrationModal(eventId) {
    currentRegistrationEventId = eventId;
    const event = events.find(e => e.id === eventId);
    
    if (!event) {
        showErrorMessage("Event not found");
        return;
    }

    const modal = document.getElementById('registrationModal');
    if (modal) {
        document.getElementById('modalEventName').textContent = event.name;
        document.getElementById('registrationForm').reset();
        modal.style.display = 'block';
    }
}

function closeRegistrationModal() {
    const modal = document.getElementById('registrationModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

function closeDetailsModal() {
    const modal = document.getElementById('detailsModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

function submitRegistration() {
    const form = document.getElementById('registrationForm');
    
    try {
        event.preventDefault();

        const formElements = form.elements;
        const name = formElements['name'].value.trim();
        const email = formElements['email'].value.trim();
        const selectedEvent = formElements['event']?.value;

        if (!name) {
            showFormError('name', 'Name is required');
            return;
        }
        if (!email) {
            showFormError('email', 'Email is required');
            return;
        }
        if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
            showFormError('email', 'Please enter a valid email');
            return;
        }

        clearFormErrors();

        const result = registerUserForEvent(currentRegistrationEventId, name, email);
        
        if (result.success) {
            showSuccessMessage(result.message);
            updateEventUI(currentRegistrationEventId);
            closeRegistrationModal();
        } else {
            showErrorMessage(result.message);
        }
    } catch (error) {
        console.error("Form submission error:", error);
        showErrorMessage("An error occurred during registration");
    }
}

function showFormError(fieldName, message) {
    const field = document.querySelector(`[name="${fieldName}"]`);
    if (field) {
        field.classList.add('error');
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.textContent = message;
        field.parentNode.appendChild(errorDiv);
    }
}

function clearFormErrors() {
    document.querySelectorAll('.error-message').forEach(el => el.remove());
    document.querySelectorAll('.error').forEach(el => el.classList.remove('error'));
}

async function submitRegistrationViaFetch(eventId, userData) {
    try {
        showLoadingSpinner(true);

        const response = await mockPostRegistration(eventId, userData);

        showLoadingSpinner(false);

        if (response.status === 'success') {
            showSuccessMessage(`Registration successful! Confirmation ID: ${response.registrationId}`);
            updateEventUI(eventId);
            return true;
        }
    } catch (error) {
        showLoadingSpinner(false);
        showErrorMessage(`Failed to submit registration: ${error.message}`);
        return false;
    }
}

function showLoadingSpinner(show) {
    const spinner = document.getElementById('loadingSpinner');
    if (spinner) {
        spinner.style.display = show ? 'block' : 'none';
    }
}

function showSuccessMessage(message) {
    const messageDiv = document.createElement('div');
    messageDiv.className = 'success-message';
    messageDiv.textContent = message;
    document.body.appendChild(messageDiv);
    
    setTimeout(() => messageDiv.remove(), 3000);
}

function showErrorMessage(message) {
    const messageDiv = document.createElement('div');
    messageDiv.className = 'error-message-global';
    messageDiv.textContent = `Error: ${message}`;
    document.body.appendChild(messageDiv);
    
    setTimeout(() => messageDiv.remove(), 4000);
}

function filterAndDisplayEvents(category) {
    if (category === 'all') {
        renderEvents(events);
    } else {
        const filtered = filterEventsByCategory(events, category);
        renderEvents(filtered);
    }
}

function showEventDetails(eventId) {
    const event = events.find(e => e.id === eventId);
    if (!event) return;

    const modal = document.getElementById('detailsModal');
    if (modal) {
        document.getElementById('detailsContent').innerHTML = `
            <h2>${event.name}</h2>
            <p><strong>Category:</strong> ${event.category}</p>
            <p><strong>Date:</strong> ${event.date}</p>
            <p><strong>Time:</strong> ${event.time}</p>
            <p><strong>Location:</strong> ${event.location}</p>
            <p><strong>Description:</strong> ${event.description}</p>
            <p><strong>Total Capacity:</strong> ${event.maxSeats}</p>
            <p><strong>Available Seats:</strong> ${event.availableSeats}</p>
            <p><strong>Registrations:</strong> ${event.registrations.length}</p>
            <hr>
            <h3>Registered Participants:</h3>
            ${event.registrations.length > 0 ? 
                `<ul>${event.registrations.map(r => `<li>${r.userName} (${r.email})</li>`).join('')}</ul>` :
                '<p>No registrations yet</p>'
            }
        `;
        modal.style.display = 'block';
    }
}

function debugRegistration() {
    console.log("=== DEBUGGING REGISTRATION ===");
    console.log("Total Events:", events.length);
    console.log("Events with registrations:");
    events.forEach(event => {
        if (event.registrations.length > 0) {
            console.log(`${event.name}: ${event.registrations.length} registrations`);
            event.registrations.forEach(reg => {
                console.log(`  - ${reg.userName} (${reg.email})`);
            });
        }
    });
    console.log("Registration Stats:", registrationTracker.getStats());
}

function testFunctionality() {
    console.log("=== TESTING FUNCTIONALITY ===");
    console.log("Test 1: Adding new event");
    addEvent(9, "Test Event", "2024-08-01", "Test", "Test Location", 20, "A test event", "12:00 PM");
    console.log("Test 2: User registration");
    const result = registerUserForEvent(1, "John Doe", "john@example.com");
    console.log("Registration result:", result);
    console.log("Test 3: Filtering music events");
    const musicEvents = getMusicEvents();
    console.log("Music events:", musicEvents.map(e => e.name));
    console.log("Test 4: Formatted event cards");
    console.log(formatEventCards().slice(0, 2));
    console.log("Test 5: Event destructuring");
    displayEventSummary(events[0]);
    console.log("Test 6: Filter with callback");
    const workshopCards = filterEventsByCategory(events, 'Workshop', event => event.name);
    console.log("Workshop events:", workshopCards);
    console.log("=== ALL TESTS COMPLETED ===");
}

document.addEventListener('DOMContentLoaded', () => {
    console.log("Initializing Community Event Portal");
    initializeEventHandlers();
    loadEventsWithAsyncAwait();
});
