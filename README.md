# display-tree

This is an app to visualize a tree defined in JSON and check its branches for duplicate ids.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Example JSON Sorting Tree Input

```
[
  {
    "description": "All CS Contacts",
    "id": "5",
    "children": [
      {
        "description": "returns",
        "id": "2",
        "children": [
          {
            "description": "offered partial refund",
            "id": "3"
          },
          {
            "description": "offered return",
            "id": "1"
          }
        ]
      },
      {
        "description": "shipment tracking",
        "id": "4"
      }
    ]
  }
]
```

