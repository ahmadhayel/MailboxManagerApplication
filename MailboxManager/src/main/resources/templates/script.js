const apiUrl = 'http://localhost:8080/recipients';

function fetchRecipients() {
    fetch(`${apiUrl}/all`)
        .then(response => response.json())
        .then(recipients => {
            const recipientList = document.getElementById('recipient-list');
            recipientList.innerHTML = '';
            recipients.forEach(recipient => {
                const recipientDiv = document.createElement('div');
                recipientDiv.innerHTML = `
                    <p>${recipient.name} - ${recipient.email}</p>
                    <button onclick="deleteRecipient(${recipient.id})">Delete</button>
                `;
                recipientList.appendChild(recipientDiv);
            });
        })
        .catch(error => console.error('Error fetching recipients:', error));
}

function addRecipient() {
    const nameInput = document.getElementById('recipient-name');
    const emailInput = document.getElementById('recipient-email');
    const name = nameInput.value.trim();
    const email = emailInput.value.trim();

    if (name && email) {
        const data = { name, email };

        fetch(`${apiUrl}/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(() => {
            nameInput.value = '';
            emailInput.value = '';
            fetchRecipients();
        })
        .catch(error => console.error('Error adding recipient:', error));
    } else {
        alert('Please provide both name and email.');
    }
}

function deleteRecipient(id) {
    if (confirm('Are you sure you want to delete this recipient?')) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        })
        .then(() => fetchRecipients())
        .catch(error => console.error('Error deleting recipient:', error));
    }
}

function fetchRecipientById() {
    const idInput = document.getElementById('recipient-id');
    const id = idInput.value.trim();

    if (id) {
        fetch(`${apiUrl}/${id}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Recipient not found');
            })
            .then(recipient => {
                const recipientDiv = document.getElementById('recipient-details');
                recipientDiv.innerHTML = `
                    <p>Name: ${recipient.name}</p>
                    <p>Email: ${recipient.email}</p>
                `;
            })
            .catch(error => console.error('Error fetching recipient by ID:', error));
    } else {
        alert('Please provide a recipient ID.');
    }
}

document.addEventListener('DOMContentLoaded', fetchRecipients);
