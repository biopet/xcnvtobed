# Manual

This tool converts a sample track within an XHMM XCNV file to a BED track. In the output the fourth column indicates
the type of CNV for the region: deletion (-1), normal (0) or duplication (1).

It can be called as follows:
```bash
java -jar <XcnvToBed-jar> \
-I input.xcnv \
-O output.bed \
-S sample
```

To open the help:
```bash
java -jar <XcnvToBed-jar> -h
General Biopet options


Options for XcnvToBed

Convert a sample track within an XHMM XCNV file to a BED track. Fourth column indicates deletion (-1), normal (0) or duplication (1) of region
Usage: XcnvToBed [options]

  -l, --log_level <value>  Level of log information printed. Possible levels: 'debug', 'info', 'warn', 'error'
  -h, --help               Print usage
  -v, --version            Print version
  -I, --Input <xcnv>       Input XCNV file
  -O, --Output <bed>       Output BED file
```


