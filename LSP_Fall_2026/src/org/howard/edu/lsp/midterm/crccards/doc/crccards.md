
# CRC Card Design Explanation

The Library collaborates with Book because the Library is responsible for maintaining the collection, finding books, and coordinating borrowing and returning, while Book manages its own information and availability state. Book does not need to collaborate with Library because its responsibilities can be completed independently, and the Library delegates state changes to it. The Library enforces the three-book borrowing limit because the limit applies to the entire collection rather than to an individual book. Keeping this rule in Library centralizes collection-level business logic and prevents the library from lending more than three books at once.
