<script lang="ts">
    import {Button, Dropdown, DropdownDivider, DropdownItem} from 'flowbite-svelte'
    import {editorStore} from "$lib/editor.store";

    const {store: editor} = editorStore

    const focus = () => $editor.chain().focus();
</script>

<Button color="light" size="xs" class="styles-button">Aa</Button>
<Dropdown triggeredBy=".styles-button">
    <DropdownItem class="font-extrabold" on:click={() => focus().toggleHeading({level: 1}).run()}>Title</DropdownItem>
    <DropdownItem class="font-bold" on:click={() => focus().toggleHeading({level: 2}).run()}>Header</DropdownItem>
    <DropdownItem class="font-semibold" on:click={() => focus().toggleHeading({level: 3}).run()}>Subheading
    </DropdownItem>
    <DropdownItem on:click={() => focus().setParagraph().run()}>Paragraph</DropdownItem>
    <DropdownDivider/>
    <DropdownItem class="text-xs" on:click={() => focus().toggleBulletList().run()}>- Bullet list</DropdownItem>
    <DropdownItem class="text-xs" on:click={() => focus().toggleOrderedList().run()}>1. Ordered list</DropdownItem>
    <DropdownDivider/>
    <DropdownItem class="text-xs" on:click={() => focus().toggleCodeBlock().run()}>Code block</DropdownItem>
</Dropdown>