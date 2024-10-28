package org.nag.protocolStack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MarshallingDecoder {
    private final Unmarshaller umarshaller;

    public MarshallingDecoder(Unmarshaller umarshaller) {
        this.umarshaller = umarshaller;
    }

    protected Object decode(ByteBuf in) throws Exception {
        int objectSize = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(), objectSize);
        ByteInput input = new ChannelBufferByteInput(buf);
        try {
            umarshaller.start(input);
            Object obj = umarshaller.readObject();
            umarshaller.finish();
            in.readerIndex(in.readerIndex() + objectSize);
            return obj;
        } finally {
            umarshaller.close();
        }
    }
}
