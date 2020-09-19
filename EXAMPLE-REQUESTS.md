
```
query bookById {
    bookById(id: "book-1") {
        id
        name
        author {
            id
            firstName
            lastName
        }
    }
}
```

```
query AuthorById {
    authorById(id: "author-1") {
        id
        firstName
        lastName
        books {
            id
            name
            author {
                id
                firstName
                lastName
            }
        }
    }
}
```