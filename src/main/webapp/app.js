document.addEventListener('DOMContentLoaded', () => {
    // Functie voor validatie van formulieren
    function validateForm(fields) {
        let isValid = true;
        fields.forEach(field => {
            const input = document.getElementById(field);
            const validationMessage = document.getElementById(`${field}-validation`);
            if (!input.value.trim()) {
                validationMessage.style.display = 'block';
                isValid = false;
            } else {
                validationMessage.style.display = 'none';
            }
        });
        return isValid;
    }
    // CREATE (POST) - Nieuw abonnement maken
    document.getElementById('createSubscriptionForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        if (!validateForm(['firstname', 'lastname', 'email'])) return;
        const formData = new FormData(event.target);
        const data = Object.fromEntries(formData.entries());
        try {
            const response = await fetch('http://localhost:8080/api/subscriptions/create', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });
            const result = await response.json();
            document.getElementById('createResponse').textContent = JSON.stringify(result, null, 2);
        } catch (error) {
            console.error('Error creating subscription:', error);
        }
    });
    // UPDATE (PUT) - Abonnement updaten
    document.getElementById('updateSubscriptionForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const id = document.getElementById('update-id').value.trim();
        const firstname = document.getElementById('update-firstname').value.trim();
        const lastname = document.getElementById('update-lastname').value.trim();
        const email = document.getElementById('update-email').value.trim();
        const phonenumber = document.getElementById('update-phonenumber').value.trim();
        if (!id) {
            alert("Subscription ID is required for update!");
            return;
        }
        // Verzamelen van alle velden om door te sturen
        const updatedSubscription = {
            firstname: firstname,
            lastname: lastname,
            email: email,
            phonenumber: phonenumber
        };
        try {
            const response = await fetch(`http://localhost:8080/api/subscriptions/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedSubscription)
            });
            const result = await response.json();
            document.getElementById('updateResponse').textContent = JSON.stringify(result, null, 2);
        } catch (error) {
            console.error('Error updating subscription:', error);
        }
    });
    // DELETE - Abonnement verwijderen
    document.getElementById('deleteSubscriptionForm').addEventListener('submit', async (event) => {
        event.preventDefault();
        const id = document.getElementById('delete-id').value.trim();
        if (!id) {
            alert("Subscription ID is required for deletion!");
            return;
        }
        try {
            const response = await fetch(`http://localhost:8080/api/subscriptions/${id}`, {
                method: 'DELETE'
            });
            document.getElementById('deleteResponse').textContent = response.ok
                ? "Subscription successfully deleted."
                : "Failed to delete subscription.";
        } catch (error) {
            console.error('Error deleting subscription:', error);
        }
    });
    // FETCH ALL SUBSCRIPTIONS
    document.getElementById('fetchSubscriptionsButton').addEventListener('click', async () => {
        try {
            const response = await fetch('http://localhost:8080/api/subscriptions');
            const subscriptions = await response.json();
            document.getElementById('subscriptions').textContent = JSON.stringify(subscriptions, null, 2);
        } catch (error) {
            console.error('Error fetching subscriptions:', error);
        }
    });
});