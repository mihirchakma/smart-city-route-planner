package com.smartcity.model;

/**
 * Manages location data using a self-balancing AVL Tree.
 * This ensures that lookups, insertions, and deletions are efficient (O(log n)).
 */

public class LocationAVLTree {
    private LocationNode root;

    // Helper function to get the height of a node
    private int getHeight(LocationNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Helper function to get the balance factor of a node
    private int getBalanceFactor(LocationNode node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // Right rotate subtree rooted with y
    private LocationNode rightRotate(LocationNode y) {
        LocationNode x = y.left;
        LocationNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // Left rotate subtree rooted with x
    private LocationNode leftRotate(LocationNode x) {
        LocationNode y = x.right;
        LocationNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Public method to add a location
    public void addLocation(String name) {
        root = insertNode(root, name);
    }

    private LocationNode insertNode(LocationNode node, String name) {
        // 1. Standard BST insertion
        if (node == null) {
            return new LocationNode(name);
        }

        if (name.compareTo(node.locationName) < 0) {
            node.left = insertNode(node.left, name);
        } else if (name.compareTo(node.locationName) > 0) {
            node.right = insertNode(node.right, name);
        } else {
            // Duplicate names are not allowed
            return node;
        }

        // 2. Update height of this ancestor node
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 3. Get the balance factor to check if this node became unbalanced
        int balance = getBalanceFactor(node);

        // 4. If unbalanced, there are 4 cases
        // Left - Left Case
        if (balance > 1 && name.compareTo(node.left.locationName) < 0) {
            return rightRotate(node);
        }
        // Right - Right Case
        if (balance < -1 && name.compareTo(node.right.locationName) > 0) {
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && name.compareTo(node.left.locationName) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left Case
        if (balance < -1 && name.compareTo(node.right.locationName) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // In-order traversal to get sorted list of locations
    public void displayAllLocations() {
        System.out.println("--- All Locations (Sorted Alphabetically) ---");
        if (root == null) {
            System.out.println("No locations have been added yet.");
        } else {
            inOrderTraversal(root);
        }
        System.out.println("-------------------------------------------");
    }

    private void inOrderTraversal(LocationNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("- " + node.locationName);
            inOrderTraversal(node.right);
        }
    }

    // Method to check if a location exists
    public boolean locationExists(String name) {
        LocationNode current = root;
        while (current != null) {
            if (name.equals(current.locationName)) {
                return true;
            } else if (name.compareTo(current.locationName) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    // (Optional but good for Member 2) Method to remove a location would go here.
    // Deletion in an AVL tree is more complex but follows a similar pattern to insertion.
}

